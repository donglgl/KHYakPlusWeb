package com.admin.layout.repository;

import java.util.List;  
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.admin.layout.vo.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
	
    // 게시글 번호만으로 댓글을 검색하는 메소드로 수정    
    List<Reply> findByBoardNumAndAdminNum(Integer boardNum, Integer adminNum);
	
}