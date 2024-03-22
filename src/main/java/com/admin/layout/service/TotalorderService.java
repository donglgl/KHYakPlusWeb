package com.admin.layout.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.admin.layout.repository.TotalOrderRepository;
import com.admin.layout.vo.TotalOrder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TotalorderService {

    @Autowired
    private TotalOrderRepository orderRepository; 

    // 회원 번호에 따른 주문 목록 조회
  //  public List<TotalOrder> findByMemNum(Long memNum) {
    //    log.info("서비스항목입니다 호출되니?.");
      //  log.info("{}", memNum);

        // TotalOrder 엔티티 목록을 조회
        //List<TotalOrder> orders = orderRepository.findByMemNum(memNum);
		//return orders;
    
    public Page<TotalOrder> findByMemNum(Long memNum, Pageable pageable) {
        return orderRepository.findByMemNum(memNum, pageable);
    }
		

  

	
}