package com.admin.layout.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.admin.layout.vo.News;


@Service
public class NewsService {
	private static String News_URL = "http://www.newsmp.com/news/articleList.html?sc_section_code=S1N6&view_type=sm";
	
	  @PostConstruct
	    public List<News> getNewsDatas() throws IOException {
	        List<News> newsList = new ArrayList<>();
	        Document document = Jsoup.connect(News_URL).get();

	        Elements contents = document.select("section div.list-block");

	        for (Element content : contents) {
	            News news = News.builder()
	                    .subject(content.select("div.list-titles").text())		// 제목
	                    .url(content.select("a").attr("abs:href"))		// 링크
	                    .build();
	            newsList.add(news);
	        }

	        return newsList;
	    }
}
