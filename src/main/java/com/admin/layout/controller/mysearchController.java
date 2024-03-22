package com.admin.layout.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.layout.entity.individualcategory;
import com.admin.layout.entity.kit;
import com.admin.layout.entity.wish;
import com.admin.layout.repository.IndcategoryRepository;
import com.admin.layout.repository.kitRepository;
import com.admin.layout.repository.memberRepository;
import com.admin.layout.repository.wishRepository;
import com.admin.layout.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class mysearchController {

	@Autowired
	private wishRepository wishrepository;
	
	@Autowired
	private kitRepository kitrepository;
	

	
	@Autowired
    private IndcategoryRepository categoryrepository;
	
//	member 이름 나이등을 출력하기 위한 추가 내용
	@Autowired
	private memberRepository memberrepository;
	
	@GetMapping("/anotherMypage")
	public String main(Model model ,HttpSession session) {
		
		List<wish> wishlist=wishrepository.findAll();
		List<kit> kitlist=kitrepository.findAll();
		List<individualcategory> buttonlist=categoryrepository.findAll();
		String id = (String)session.getAttribute("id");
		Member member = memberrepository.findByMemId(id);
		
		log.info("wishlist {}",wishlist);
		log.info("kitlist {}",kitlist);
		log.info("member {}",member);
		
		Long wishcount=wishrepository.count();
		Long kitcount=kitrepository.count();
		String dateString = member.getMemBirthday();
		String gender = (member.getMemGen().equals("M"))? "남" : "여";
		LocalDate birthDate = LocalDate.parse(dateString);
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        int age = period.getYears();
		
		model.addAttribute("wishcount", wishcount);
		model.addAttribute("kitcount", kitcount);
		
		model.addAttribute("wishlist", wishlist);
		model.addAttribute("kitlist", kitlist);
		
		model.addAttribute("buttonlist",buttonlist);
		
		model.addAttribute("member",member);
		model.addAttribute("age",age);
		model.addAttribute("gender",gender);
        
        
		return "anotherMypage/anotherMypage";
	}
	
}
