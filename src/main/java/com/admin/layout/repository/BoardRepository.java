package com.admin.layout.repository;



import org.springframework.data.domain.Page;   
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.layout.vo.Board;
import com.admin.layout.vo.Reply;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	
	    // 페이징 처리를 위해서 jpa작성 
		Page<Board> findAll(Pageable pageable);
		
		
		// title검색
		Page<Board> findByBoardTitleContaining(String keyword,
									      Pageable pageable);
		
		
		// 카테고리 검색
		Page<Board> findByboardCateContaining(String keyword,
											   Pageable pageable);


	
}
