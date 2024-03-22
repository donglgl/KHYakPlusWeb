package com.admin.layout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.layout.repository.itemRepository;
import com.admin.layout.vo.item;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DBController {

	@Autowired
	private itemRepository itemRepository;

	@GetMapping("/item")
	public String getUsersAndPaging(Model model, @RequestParam(defaultValue = "0") int pageNo) {
		log.info("paging 메인");

		// 페이지 사이즈
		int pageSize = 16;
		// 페이지 내림차순
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("itemNum").descending());

		// 아이템 목록 가져오기
		model.addAttribute("items", itemRepository.findAll());

		// 페이지로 부터 게시글 목록을 가져오기
		Page<item> list = itemRepository.findAll(pageable);
		// getContent에 리스트형태로 저장

		model.addAttribute("lists", list.getContent());

		// page변수를 이용해서 페이징 버튼을 만들 것!
		model.addAttribute("page", list);

		return "/sel/sel_List";
	}

	@GetMapping("/sel_Detail/{itemNum}")
	public String sel_detailPage(@PathVariable Long itemNum, Model model) {
		// itemNum을 사용하여 데이터베이스에서 상세 정보 조회
		item item = itemRepository.findById(itemNum).orElse(null);

		// 조회한 상세 정보를 모델에 담아서 뷰로 전달
		model.addAttribute("items", item);

		return "/sel/sel_Detail";
	}

}
