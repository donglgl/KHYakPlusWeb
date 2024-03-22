package com.admin.layout.APIController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.admin.layout.apiloginservice.KakaoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class APIController {
	@Autowired
	private KakaoService kakaoService;
	@Autowired
	private com.admin.layout.apiloginservice.NaverService NaverService;

	// (5)
	@GetMapping("/kakaologin")
	public String kakaologin(Model model) {
		log.info("카카오");
		// 내가 가지고 있는 카카오정보를 이용해서
		// 로그인 개발을 할 수 있도록 토큰을 발급 받는 내용을 작성합니다.
		// 그 토큰이 redirect url로 옵니다.

		// 카카오에 관한 처리를 할 수 있는 service 만듭니다.
		Object obj = kakaoService.getKakaoLogin();
		log.info("{}", obj);
	
		model.addAttribute("kakaoUrl", obj);
		model.addAttribute("naverUrl", NaverService.getNaverLogin());

		return "login/login";

	}

	// 네이버가 나한테 인가코드를 리다이렉트 하는 코드
	@GetMapping("/naver")
	public String naverkakaologin(String code, Model model) {

		log.info("잘받아왓닝? 네이버얌? {}", code);

		try {
			NaverService.getNaverInfo(code);
		} catch (Exception e) {
		}

		model.addAttribute("naverUrl", NaverService.getNaverLogin());

		return "login/login";
	}

	// (3) 카카오가 나한테 토큰 번호를 리다이렉트 하는 것입니다.
	// 카카오가 접속할 수 있도록 허용하는 코드를 줍니다.
	@GetMapping("/kakao")
	public String kakao(String code, Model model) {
		log.info("카카오! 잘 받음? code-> {}", code);

		model.addAttribute("nickname", kakaoService.getKakaoInfo(code).getNickname());

		// 받아온 인가 코드를 이용해서 계정의 정보를 가져오는 서비스를 실행
		// 서비스명 :getKakaoInfo()
		return "login/login";

	}

	// accesstoken 은 만료 시간이 존재하기 때문에 발급받은 리프레쉬 토큰을 저장해두고,
	// 액세스 토큰을 갱싱하는 형태로 사용합니다
	// accesstoken :토큰을 발급 받을 때 사용하는 것
	// 갱신할 때 사용하는 토큰 ( acesstoken 을 갱신할 때 )

}