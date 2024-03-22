package com.admin.layout.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;


import com.admin.layout.repository.NoticeRepository;
import com.admin.layout.vo.Notice;

@Service
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;

	// Notice 삭제
	public Notice delete(Integer noticeNum) {

		// log.info("delete 실행");

		Notice target = noticeRepository.findById(noticeNum).orElse(null);

		if (target == null) {

			// log.info("잘못된 요청! {}번",id);
			return null;
		}

		noticeRepository.delete(target);

		return target;
	}

}
