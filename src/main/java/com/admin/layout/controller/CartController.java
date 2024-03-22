package com.admin.layout.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.admin.layout.service.CartService;
import com.admin.layout.vo.CartVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CartController {

	@Autowired
	private CartService cartservice;
	

	
	@GetMapping("/Cart")
	public String Cart(Model model,HttpSession session) {
		
		log.info("cart controller");
		String id = (String)session.getAttribute("id");
		
		ArrayList<CartVO> cart= cartservice.printCart(id);
		log.info("cart {}",cart);
		model.addAttribute("carts", cart);
		return "payAndmarket/shopping_cart";
	}
	
	@GetMapping("/Cart/insert")
	public String inCart(@RequestParam("itemName") String itemName,@RequestParam("price") Long price,
			@RequestParam("quantity") Long quantity,Model model,HttpSession session) {
		String id = (String)session.getAttribute("id");
		
		cartservice.insertCart(id, quantity, price, itemName);
		
		return "redirect:/Cart";
	}
}
