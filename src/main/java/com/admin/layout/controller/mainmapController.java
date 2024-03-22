package com.admin.layout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class mainmapController {

	@org.springframework.beans.factory.annotation.Value("${kakao.map.secret}")
	private String mapapi;

	@GetMapping("/")
	public String home(Model model) throws Exception {

		return "main";
	}

	@GetMapping("/map")
	public String map(Model model) {

		String apikey = "//dapi.kakao.com/v2/maps/sdk.js?appkey="+mapapi+"&libraries=services";
		model.addAttribute("kakaomapapi", apikey);
		return "map/map";
	}

}
