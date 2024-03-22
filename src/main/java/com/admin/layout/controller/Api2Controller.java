package com.admin.layout.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.layout.service.ApiService2;
import com.admin.layout.vo.Pill;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
public class Api2Controller {

	@Autowired
	private ApiService2 apiService;

	// 검색페이지
	@GetMapping("/search")
	public String search1() {
		return "/info/search";
	}

	// 전체검색
	@GetMapping("/processSearch")
	public String search(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(value = "all", required = false) String all,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "ent", required = false) String ent, Model model) {
		try {
			if (all != null) {
				// 검색어를 분리하여 제품명과 제조사로 나누기
				String[] keywords = all.split("\\s+");
				ent = keywords[0];
				if (keywords.length > 1) {
					title = keywords[1];
				}
			}

			List<Pill> pillList;
			if (title != null && ent != null) {
				// 제품명과 제조사를 각각 검색하여 전체 검색을 수행하는 메서드 호출
				pillList = apiService.searchPillsByAll(all);
			} else {
				pillList = apiService.getResultList(model, title, ent, pageNo);
			}
			// 페이지 네이션 설정
			addPaginationAttributes(model, title, pageNo, pageSize);
			// 검색 결과를 모델에 추가
			model.addAttribute("list", pillList);
			model.addAttribute("pageSize", pageSize);

		} catch (Exception e) {
			// 예외 처리
			e.printStackTrace();
			model.addAttribute("error", "An error occurred while processing the search.");
		}
		return "/info/result_Photo";
	}

	// 검색결과 리스트 페이지
	@GetMapping("/processSearch/list")
	public String search1(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5") int pageSize,
			@RequestParam(value = "all", required = false) String all,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "ent", required = false) String ent, Model model) {
		try {
			if (all != null) {
				// 검색어를 분리하여 제품명과 제조사로 나누기
				String[] keywords = all.split("\\s+");
				title = keywords[0];
				if (keywords.length > 1) {
					ent = keywords[1];
				}
			}

			List<Pill> pillList;
			if (title != null && ent != null) {
				// 제품명과 제조사를 각각 검색하여 전체 검색을 수행하는 메서드 호출
				pillList = apiService.searchPillsByAll(all);
			} else {
				pillList = apiService.getResultList(model, title, ent, pageNo);
			}
			// 페이지 네이션 설정
			addPaginationAttributes(model, title, pageNo, pageSize);
			// 검색 결과를 모델에 추가
			model.addAttribute("list", pillList);
			model.addAttribute("pageSize", pageSize);

		} catch (Exception e) {
			// 예외 처리
			e.printStackTrace();
			model.addAttribute("error", "An error occurred while processing the search.");
		}
		return "/info/result_List";
	}

	// 예외 처리 메서드
	private void handleException(Model model, Exception e) {
		e.printStackTrace();
		model.addAttribute("error", e.getMessage());
	}

	// 페이징 관련 모델 속성 추가 메서드
	private void addPaginationAttributes(Model model, String title, int pageNo, int pageSize) {
		try {
			int totalCount = apiService.getTotalCount(title, "", "", "", "", "", "", 1, 10);

			System.out.println("total" + totalCount);
			System.out.println("page" + pageSize);
			int totalPages = (int) Math.ceil((double) totalCount / pageSize);
			System.out.println("totalPages" + totalPages);
			List<Integer> pagedPages = IntStream
					.rangeClosed(Math.max(1, pageNo - 2), Math.min(Math.max(1, pageNo - 2) + 4, totalPages)).boxed()
					.collect(Collectors.toList());

			model.addAttribute("title", title);
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("pages", pagedPages);

			System.out.println("title" + title);
			System.out.println("currentPage" + pageNo);
			System.out.println("totalPages" + totalPages);
			System.out.println("pages" + pagedPages);

		} catch (Exception e) {
			handleException(model, e);
		}
	}
}