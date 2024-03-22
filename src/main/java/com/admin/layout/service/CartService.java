package com.admin.layout.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.layout.repository.CartRepository;
import com.admin.layout.repository.itemRepository;
import com.admin.layout.vo.CartVO;
import com.admin.layout.vo.item;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	


	public ArrayList<CartVO> printCart(String id){
		ArrayList<CartVO> list = cartRepository.findByMemId(id);
		return list;
	}
	
	public void insertCart(String id,Long count,Long price,String itemName) {
		CartVO vo = new CartVO();
		vo.setMemId(id);
		vo.setCount(count);
		vo.setItemPrice(price);
		vo.setItemName(itemName);
		cartRepository.save(vo);
	}
}
