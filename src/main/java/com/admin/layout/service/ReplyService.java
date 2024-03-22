package com.admin.layout.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.admin.layout.repository.ReplyRepository;
import com.admin.layout.repository.BoardRepository;
import com.admin.layout.vo.Board;
import com.admin.layout.vo.Reply;

@Service
public class ReplyService {

    @Autowired
    private BoardRepository boardRepository;
    
    @Autowired
    private ReplyRepository replyRepository;
    
    
    // 답변 유무
    public Reply saveReply(Integer boardNum, Reply reply) {
    	
        // 답변을 게시글에 연결
        Board board = boardRepository.findById(boardNum)
        		                      .orElse(null);

        // 답변 저장 로직
        reply.setBoardNum(boardNum); // 필요에 따라 적절한 setter 사용
        Reply savedReply = replyRepository.save(reply);

        // 게시글의 답변 상태를 '답변완료'로 업데이트
        board.setBoardCheck("답변완료");
        boardRepository.save(board);

        return savedReply; // 저장된 Reply 객체를 반환
    }
    
}







