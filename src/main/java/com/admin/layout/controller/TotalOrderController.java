package com.admin.layout.controller;

import java.util.Collections;  
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.layout.service.TotalorderService;
import com.admin.layout.vo.TotalOrder;

import org.springframework.data.domain.Sort;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TotalOrderController {
	
	@Autowired
	private TotalorderService orderService;
	@GetMapping("/totalOrders")
	public String totalOrders(HttpSession session, Model model, 
	        @RequestParam(defaultValue="0")int page) {
	    int pageSize=10;
		
		
		Long memNum = (Long) session.getAttribute("login_memNum");
	    log.info("세션오나요? {}", memNum); // memNum 로그 출력"
	    
	    if (memNum == null) {
	        return "payAndmarket/login"; // 로그인 페이지로 리다이렉션
	    }
	    
	    Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "order_Num"));
	    Page<TotalOrder> orders = orderService.findByMemNum(memNum, pageable);
	    
	    

	   log.info("{}",orders);
	    // list를 로깅

	    model.addAttribute("page", orders);

	    return "payAndmarket/history";
	}}