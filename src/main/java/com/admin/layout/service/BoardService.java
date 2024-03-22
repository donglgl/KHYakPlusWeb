package com.admin.layout.service;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;

import com.admin.layout.vo.Board;
import com.admin.layout.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	// Board 삭제
	public Board delete(Integer boardNum) {

		// log.info("delete 실행");

		Board target = boardRepository.findById(boardNum).orElse(null);

		if (target == null) {

	    // log.info("잘못된 요청! {}번",id);

			return null;
		}

		boardRepository.delete(target);

		return target;
	}

}
