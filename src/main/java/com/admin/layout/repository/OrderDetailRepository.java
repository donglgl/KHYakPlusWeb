package com.admin.layout.repository;

import java.util.List;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.admin.layout.vo.OrderDetail;

import groovy.util.logging.Slf4j;
@Slf4j
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
  



	List<OrderDetail> findByMemName(String loginName);
	   @Query("SELECT od FROM OrderDetail od WHERE od.memName = :loginName")
	    List<OrderDetail> findByMemNameUsingQuery(String loginName);
}
