package com.admin.layout.service;

import java.util.ArrayList;  
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.admin.layout.repository.OrderDetailRepository;
import com.admin.layout.vo.OrderDetail;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@Transactional
public class OrderDetailService {

  

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    
 
    
    
    

    public List<OrderDetail> findByMemName(String loginName) {
        List<OrderDetail> orderDetails = orderDetailRepository.findByMemName(loginName);
        log.info("사용자이름 이용해서 조회한 상세상품 {}: {}", loginName, orderDetails);
        return orderDetails;
    }


    //상품저장.
	public OrderDetail save(OrderDetail orderDetail) {
		return  orderDetailRepository.save(orderDetail);
		
	}
    
    }
    
    
    



	

	






