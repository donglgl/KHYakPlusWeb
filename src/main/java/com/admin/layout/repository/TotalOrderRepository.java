package com.admin.layout.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.admin.layout.vo.TotalOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface TotalOrderRepository extends JpaRepository<TotalOrder, Long> {
	 @Query(value = "SELECT * FROM TotalOrder WHERE TotalOrder.mem_num = :memNum", nativeQuery = true)
	    Page<TotalOrder> findByMemNum(@Param("memNum") Long memNum, Pageable pageable);

	
	

	//	List<TotalOrder> findByMemNum(Long memNum, Sort sort);

}
