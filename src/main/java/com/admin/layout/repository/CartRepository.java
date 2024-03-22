package com.admin.layout.repository;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.layout.vo.CartVO;

public interface CartRepository extends JpaRepository <CartVO,Long> {
	
	ArrayList<CartVO> findByMemId(String id);
}
