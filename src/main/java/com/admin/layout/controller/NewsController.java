package com.admin.layout.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.admin.layout.service.NewsService;
import com.admin.layout.vo.News;



@Controller
public class NewsController {
	
	private final NewsService newsService;

	public NewsController(NewsService newsService) {
		this.newsService = newsService;
	}

	@GetMapping("/news")
	public String news(Model model) throws Exception {
		List<News> newsList = newsService.getNewsDatas();
		model.addAttribute("news", newsList);
		return "news";
	}
}
