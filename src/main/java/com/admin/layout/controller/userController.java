package com.admin.layout.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.admin.layout.apilogin.DTO.KakaoDTO;
import com.admin.layout.apiloginservice.KakaoService;
import com.admin.layout.apiloginservice.NaverService;
import com.admin.layout.repository.memberRepository;
import com.admin.layout.service.userService;
import com.admin.layout.vo.Member;

import lombok.extern.slf4j.Slf4j;

//유저의 모든 컨트롤러 로그인,회원가입,마이페이지 등등...

@Slf4j
@Controller
public class userController {

	@Value("${com.example.upload.path}") // application.properties의 변수
	private String uploadPath;

	@Autowired
	private KakaoService kakaoService;

	@Autowired
	private NaverService naverService;

	@Autowired
	private userService userservice;

	@Autowired
	private memberRepository memberrepository;

//	회원가입
	@GetMapping("/sigin")
	public String sigin(Model model) {
		log.info("join()실행");
		model.addAttribute("userInfo", new Member());
		return "/sign/sign3";
	}

//	회원가입
	@PostMapping("/sigin")
	public String sigin(Member member, HttpServletRequest request, @RequestPart("file") MultipartFile files) {
		log.info("singpost()실행 {}" + member);
		try {
			userservice.memberJoin(member, request, files);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}

//아이디 중복 확인
	// 아이디 중복체크
	@PostMapping("/checkId")
	@ResponseBody
	public boolean idCheck(@RequestParam("memId") String memId) {
		log.info("{} idCheck실행" + memId);
		boolean check;
		check = userservice.checkId(memId);
		return check;
	}

	// 닉네임 중복확인
	@PostMapping("/nickNameCheck")
	@ResponseBody
	public boolean nickNameCheck(@RequestParam("nickname") String nickname) {
		log.info("{} nicknameCheck실행" + nickname);
		boolean check;
		check = userservice.checknickname(nickname);
		return check;
	}

//	로그인
	@GetMapping("/login")
	public String login(Model model) {

		log.info("loginpage()실행");
		// api 내용으로 넘어가는 코드 작성
		Object obj = kakaoService.getKakaoLogin();
		log.info("{}", obj);

		model.addAttribute("kakaoUrl", obj);
		model.addAttribute("naverUrl", naverService.getNaverLogin());

		return "/login/login";
	}

	@GetMapping("/login/fail")
	public String loginfail() {
		return "/login/loginAlter";
	}

	// 카카오 네이버 추가 로그인
	@GetMapping("/kakao/auto")
	public String kakaoAuto(String code, HttpSession session) {

		log.info("레스트로 오니?");
		log.info("{}", code);

		// 토큰값 받는 메서드 만들기
		KakaoDTO dto = kakaoService.getKakaoInfo(code);

		log.info("dto kakao메서드:{} ", dto);

		Member member = userservice.login(dto.getEmail());

		if (member != null) {
			if (member.getMemId().equals("admin")) {
				session.setAttribute("id", member.getMemId());
				return "redirect:/admin";
			}
			session.setAttribute("id", member.getMemId());
			return "redirect:/";
		} else {
			return "redirect:/login/fail";
		}
	}

	// 네이버가 나한테 인가코드를 리다이렉트 하는 코드
	@GetMapping("/naver/auto")
	public String naverkakaologin(String code, Model model) {

		log.info("잘받아왓닝? 네이버얌? {}", code);

		try {
			naverService.getNaverInfo(code);
		} catch (Exception e) {
		}

		return "네이버인데?";
	}

//	로그인
	@PostMapping("/login")
	public String login(String memId, String memPwd, HttpServletRequest request) {

		log.info("login 확인{}", memId, memPwd, request.toString());

		// 세션에서 DTO 객체를 가져옴
		KakaoDTO dto = (KakaoDTO) request.getSession().getAttribute("kakaoDTO");
		log.info("post들어온거 맞니?{}:", dto);
		// DTO 객체를 사용하여 로그인 처리 등을 수행
		log.info("login 확인");

		boolean check = userservice.login(memId, memPwd);
		HttpSession session = request.getSession();
		if (check) {
			if (memId.equals("admin")) {
				session.setAttribute("id", memId);
				return "redirect:/admin";
			}
			session.setAttribute("id", memId);
			return "redirect:/";
		} else {
			return "redirect:/login/fail";
		}

	}

// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		log.info("logout 실행");
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}

//	비밀번호 찾기
	@GetMapping("/login2")
	public String findPwd() {

		log.info("login2()실행");

		return "/login/login2";
	}

//	마이페이지

	@PostMapping("/mypage")
	public String update(@RequestParam("memPh") String memPh, @RequestParam("memBirthday") String userBirthdate,
			@RequestParam("memEmail") String userEmail, @RequestParam("memAddr") String memAddr,
			@RequestParam("detailAddr") String detailAddr, @RequestPart("file") MultipartFile files,
			HttpServletRequest request) {
		log.info("update 실행");
		String userId = (String) request.getSession().getAttribute("id");

		Member logUser = userservice.getLoggedInUserData(userId);

		String sourceFileName = files.getOriginalFilename();
		log.info("파일 이름 확인  {}", sourceFileName);
		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
		File destinationFile;
		String destinationFileName;
		String fileUrl = uploadPath;

		do {
			destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
			destinationFile = new File(fileUrl + destinationFileName);
		} while (destinationFile.exists());

		destinationFile.getParentFile().mkdirs();
		try {
			files.transferTo(destinationFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logUser.setMemPh(memPh);
		logUser.setMemBirthday(userBirthdate);
		logUser.setMemEmail(userEmail);
		logUser.setMemAddr(memAddr);
		logUser.setDetailAddr(detailAddr);
		logUser.setMemberImg(destinationFileName);
		logUser.setMemberImgName(sourceFileName);
		logUser.setMemberImgUrl(fileUrl);
		memberrepository.save(logUser);

		log.info("update{}", logUser);

		return "redirect:/mypage";
	}

	@GetMapping("/mypage")
	public String mypage(Model model, HttpServletRequest request) {

		log.info("mypage 실행");
		// 세션에서 사용자 ID 가져오기
		String userId = (String) request.getSession().getAttribute("id");

		Member logUser = userservice.getLoggedInUserData(userId);

		model.addAttribute("mylist", logUser);

		System.out.println(userId);
		System.out.println("data" + logUser);

		return "/mypage/mypage";
	}

	@GetMapping("/mypage/redirect")
	public String redirect(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("id");

		// 로그 출력
		System.out.println(userId);

		// 회원 삭제
		userservice.deleteMemberById(userId);

		session.invalidate();

		// 메인 페이지로 리다이렉트
		return "redirect:/";
	}

}
