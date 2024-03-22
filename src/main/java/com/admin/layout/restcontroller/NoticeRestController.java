package com.admin.layout.restcontroller;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import com.admin.layout.service.NoticeService;
import com.admin.layout.vo.Notice;



@RestController
public class NoticeRestController {

	// 중복방지용
	
	// 데이터베이스 접근 해야되니깐 Reository필요하다 >> 대신 Service
	@Autowired
	private NoticeService noticeService;
	
	// 삭제하기
	@DeleteMapping("/notice/delete/{noticeNum}")
	public ResponseEntity<Notice> delete(@PathVariable Integer noticeNum) {

		Notice deleted = noticeService.delete(noticeNum);

		return deleted != null ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
				               : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		
	}

	
	
}





