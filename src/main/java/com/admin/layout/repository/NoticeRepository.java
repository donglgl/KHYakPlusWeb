package com.admin.layout.repository;


import org.springframework.data.domain.Page;   
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.layout.vo.Notice;


@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer>{
	
	
	    // 페이징 처리를 위해서 jpa작성 
		Page<Notice> findAll(Pageable pageable);
		
		
		// title검색
		Page<Notice> findByNoticeTitleContaining(String keyword,
									              Pageable pageable);
		
		
		// 카테고리 검색
		Page<Notice> findByNoticeCateContaining(String keyword,
											   Pageable pageable);



	
}
