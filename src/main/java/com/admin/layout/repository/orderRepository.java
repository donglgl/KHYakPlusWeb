package com.admin.layout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.layout.vo.Order;



public interface orderRepository extends JpaRepository<Order,Long> {

	Order findByOrderNum(Long orderNum);
	
}
