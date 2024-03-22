package com.admin.layout.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.admin.layout.repository.memberRepository;
import com.admin.layout.vo.Member;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class ItemController {
	
	@Autowired
	memberRepository memberRepository;
	 @GetMapping("/fakeLogin")
	    public String fakeLogin(HttpServletRequest request) {
	       
	        
	        // 임의의 사용자 정보	 (정미영)를 이름으로 가져옵니다.
	        Member fakeUser = memberRepository.findByMemName("정미영");
	        HttpSession session = request.getSession();
	        // 가져온 사용자 정보를 세션에 저장합니다.
	        if (fakeUser != null) {
	            session.setAttribute("login_memNum", fakeUser.getMemNum());
	            session.setAttribute("login_id", fakeUser.getMemId());
	            session.setAttribute("login_name", fakeUser.getMemName());
	            session.setAttribute("login_addr", fakeUser.getMemAddr());
	            session.setAttribute("login_phone", fakeUser.getMemPh());
	            
	            log.info("로그인 성공: 사용자 memNum = {}", fakeUser.getMemNum());
	            
	        
	 }
			return "redirect:/pay";
	 }
}

	    
