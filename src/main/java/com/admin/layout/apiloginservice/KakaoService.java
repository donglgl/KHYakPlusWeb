package com.admin.layout.apiloginservice;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.admin.layout.apilogin.DTO.KakaoDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KakaoService {
	// 프로퍼티스에서 값을 꺼내와서 변수에 직접 저장
	@Value("${kakao.client.id}")
	private String KAKAO_CLIENT_ID;

	@Value("${kakao.client.secret}")
	private String KAKAO_CLIENT_SECRET;

	@Value("${kakao.redirect.url}")
	private String KAKAO_REDIRECT_URL;
	
	// 인가 코드를 요청하는 고정된 변수값	
	private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com";

	public Object getKakaoLogin() {
		return KAKAO_AUTH_URI + "/oauth/authorize?" + "client_id=" + KAKAO_CLIENT_ID + "&redirect_uri="
				+ KAKAO_REDIRECT_URL + "&response_type=code";
	}

	// 인가 코드를 매개변수로 받아서 실재 내 정보를 받아오는 메서드 만들기

	public KakaoDTO getKakaoInfo(String code) {
		log.info("getKakaoInfo() 실행");
		log.info("인가 코드 : {}", code);
		if (code == null) {
			try {
				throw new Exception("인가 코드 안 옴");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 토큰을 받아오면 저장하는 변수
		String accessToken = "";
		String refreshToken = "";

		// 카카오에서 받아온 데이터를 기준으로 dto를 생성해도 좋다.
		KakaoDTO dto = null;

		// 사용자 정보 얻으려면 여러 정보가 필요하다.
		try {

			// 토큰을 사용해서 정보를 가져온다.
			// HttpHeaders 객체 생성
			HttpHeaders headers = new HttpHeaders();
			// 토큰 발급
			headers.add("Content-type", "application/x-www-form-urlencoded");
			// MultiValueMap : map 형식이지만 여러 정보를 입력한 순서대로 저장된다.
			// LinkedMultiValueMap : 실제 객체를 저장한다.
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>(); // MultiValueMap 객체 생성
			params.add("grant_type", "authorization_code"); // grant_type 파라미터 추가
			params.add("client_id", KAKAO_CLIENT_ID); // client_id 파라미터 추가
			params.add("client_secret", KAKAO_CLIENT_SECRET); // client_secret 파라미터 추가
			params.add("code", code); // code 파라미터 추가
			params.add("redirect_uri", KAKAO_REDIRECT_URL); // redirect_uri 파라미터 추가

			// 전송할 때 rest API를 편하게 사용할 수 있도롯 스프링이 지원해주는
			// restTemplate클래스를 이용해서
			// HttpEntity(request 헤더와 바디 부분, response 헤더와 바디 부분을 가짐)
			// 요청에 따라서 객체를 생성
			RestTemplate restTemplate = new RestTemplate();

			// 전송 준비 완료

			// HttpEntity가 데이터를 종합(통합)해서 담을 수 있다.
			HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

			// 파라미터와 헤더를 묶어서 HttpEntity로 저장했으므로 찐 전송 단계
			// RestTemplate : 동기식. 시간에 대해서도 설정할 수 있다.
			// exchange() : http 요청을 보내고 해당 요청에 대한 응답을 받는데 사용하는 메서드
			// 전송 방식, 어떤 데이터인지??? 등
			ResponseEntity<String> response = restTemplate.exchange(KAKAO_AUTH_URI + "/oauth/token", HttpMethod.POST,
					httpEntity, String.class);

			// json 타입으로 변환해서 파싱하겠다.
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());

			accessToken = (String) jsonObj.get("access_token");
			refreshToken = (String) jsonObj.get("refresh_token");

			log.info("accessToken: {} ", accessToken);
			log.info("refreshToken: {} ", refreshToken);
			log.info("잘 나왔니? 카카오 토큰아 {} ", response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("코드 정상 실행됨");

		dto = getUserInfoWithToken(accessToken);

		log.info("dto :{}", dto);
		
		return dto;
	}

	// 사용자 정보 가져오기
	private KakaoDTO getUserInfoWithToken(String accessToken) {
		String nickname="";
		String email="";
		long id = 0;
		log.info("getUserInfoWithToken() 실행");
		try {
		// 1. HttpHeader 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization","Bearer "+accessToken );
		headers.add("Content-type"
				, "application/x-www-form-urlencoded;charset=utf-8");
			
		// 2. HttpHeader를 httpEntity담기
			// RestTemplate : 전송을 도와준다.
		RestTemplate rt = new RestTemplate();
			
				HttpEntity<MultiValueMap<String, String>> httpEntity
			= new HttpEntity<>(headers);
			
		// 3. 응답받는 responseEntity<String>
		// 4. 전송 방식 post
		ResponseEntity<String> re= 
				rt.exchange("https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				httpEntity, 
				String.class);
		log.info("응답받은 entity:{}",re);
		
		// 5. 응답받은 데이터 파싱
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj;
		
			jsonObj = (JSONObject) jsonParser.parse(re.getBody());
		
		JSONObject account = (JSONObject) jsonObj.get("kakao_account");
		JSONObject profile = (JSONObject) account.get("profile");
		
		log.info("kakao_account: {} ", account);
		
		id = (long) jsonObj.get("id");
		email = String.valueOf(account.get("email"));
		nickname = String.valueOf(profile.get("nickname"));
		
		
		// 6. log 로그값 찍기
		log.info("이메일: {}" , email);
		
		// 7. kakaodto에 저장하기		
		log.info("with 메서드{}:", jsonObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return KakaoDTO.builder().
				id(id).email(email).nickname(nickname).build();
	}
}
/*
 * # 토큰 
 * - 서버에서 클라이언트를 구분하기 위한 유일한 값을 서버에 요청한다. 
 * - 서버가 토큰 생성 
 * - 클라이언트에게 제공 
 * - 클라이언트는 토큰을 소지 
 * - 클라이언트 토큰과 함께 서버에 요청 
 * - 서버는 토큰으로 유용한 사용자인지 검증
 * 
 * # 클라이언트 					# 서버 
 * 1. 로그인 요청 				-> 	인가해준 값(아이디,시크릿값 등)을 확인함 
 * 2. 						<- 	토큰 생성 후 응답 (주민번호처럼)
 * 3. 토큰을 저장 
 * 4. 토큰 정보와 함께 정보할 요청값 	-> 
 * 5. 토큰 검사 
 * 6. 						<- 	응답 (response해서)
 */