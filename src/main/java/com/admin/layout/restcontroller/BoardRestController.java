package com.admin.layout.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;   
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.admin.layout.vo.Board;

import com.admin.layout.service.BoardService;




@RestController
public class BoardRestController {

	// 중복방지용
	
	// 데이터베이스 접근 해야되니깐 Reository필요하다 >> 대신 Service
	@Autowired
	private BoardService boardService;
	
	// 삭제하기
	@DeleteMapping("/board/delete/{boardNum}")
	public ResponseEntity<Board> delete(@PathVariable Integer boardNum) {

		Board deleted = boardService.delete(boardNum);

		return deleted != null ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
				               : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

}





