package com.admin.layout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class ReturnController {
//여긴 반품관련해서 모든것이 진행될것입니다. 반품내역 반품신청
	
@GetMapping("/return")
public String returnRequest() {
	
	
	return "payAndmarket/return";
	
}
@GetMapping("/returnOrder")
public String returnHistory(){
	return "payAndmarket/return_history";
	
}
}
