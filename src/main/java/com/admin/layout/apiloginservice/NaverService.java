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

import com.admin.layout.apilogin.DTO.NaverDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NaverService {

	@Value("${naver.client.id}")
	private String NAVER_CLIENT_ID;
	
	@Value("${naver.client.secret}")
	private String NAVER_CLIENT_SECRET;
	
	@Value("${naver.redirect.url}")
	private String NAVER_REDIRECT_URL;
	
	// 인가코드를 요청하는 고정된 변수값
	private final static String NAVER_AUTH_URI="https://nid.naver.com";
	
	public String getNaverLogin() {
		
		log.info("getNaverLogin() 실행");
		log.info("clientid: {}",NAVER_CLIENT_ID);
				
				
		return NAVER_AUTH_URI + "/oauth2.0/authorize"
			   + "?client_id=" +NAVER_CLIENT_ID
			   + "&redirect_uri=" +NAVER_REDIRECT_URL
			   + "&response_type=code";
	}

	public void getNaverInfo(String code) throws Exception {
		
		log.info("getNaverInfo메서드실행()");
		log.info("인가코드: {}",code);
		
		if(code == null) throw new Exception("인가코드가 오지 않았습니다.");
		
		// 액세스토근은 만료시간이 존재하기 때문에 발급받은
		// 리프레쉬 토근을 저장해 두고 액세스토근을 갱신하는 형태로 
		// 사용한다.
		
		//accesstoken : 토근 발급 받을 때 사용하는 것!
		String accessToken = "";
		// refreshToken : accesstoken을 갱신할 때 사용하는것!
		String refreshToken="";
		
		try {
			// 1. 헤더만들기
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", "application/x-www-form-urlencoded");

			// 2. 요청파라미터 작성하기 
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>(); // MultiValueMap 객체 생성
			params.add("grant_type", "authorization_code"); // grant_type 파라미터 추가
			params.add("client_id", NAVER_CLIENT_ID); // client_id 파라미터 추가
			params.add("client_secret", NAVER_CLIENT_SECRET); // client_secret 파라미터 추가
			params.add("code", code); // code 파라미터 추가
			params.add("redirect_uri", NAVER_REDIRECT_URL); // redirect_uri 파라미터 추가

			// 3. 전송 준비 
			RestTemplate restTemplate = 
								new RestTemplate();
			
			// 4.헤더와 파라미터 합치기 
			HttpEntity<MultiValueMap<String, String>> httpEntity
				= new HttpEntity<>(params,headers);
			
			// 5. httpEntity 전송이 되면 응답받는 객체로 받아준다.
			ResponseEntity<String> response = 
							restTemplate.exchange(
								NAVER_AUTH_URI +"/oauth2.0/token"
								,HttpMethod.POST
								,httpEntity
								,String.class
								);
			log.info("response: {}" , response);
			
			// 6. 응답받은 객체를 이용해서 파싱하기 
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser
								 .parse(response.getBody());
			
			accessToken  = (String) jsonObj.get("access_token");
			refreshToken  = (String) jsonObj.get("refresh_token");
			
			
			log.info("accessToken: {}",accessToken);
			log.info("refreshToken: {}",refreshToken);
			
			// 사용자 정보
			NaverDTO dto = getUserInfoWithToken(accessToken);
			
			log.info("전달받은 dto: {}", dto);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	// 사용자 정보를 객체화 시켜서 반환 받기 
	private NaverDTO getUserInfoWithToken(String accessToken) throws ParseException {
		
		log.info("전달받은 토큰 값: {}" , accessToken);
		// 1. 헤더 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization","Bearer "+ accessToken );
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpEntity를 생성해서 헤더를 담는다.
		HttpEntity<MultiValueMap<String, String>> httpEntity
								= new HttpEntity<>(headers);
		
		// 전송하기 
		RestTemplate rt = new RestTemplate();
		
		ResponseEntity<String> response = rt.exchange("https://openapi.naver.com/v1/nid/me"
					, HttpMethod.POST
					, httpEntity
					, String.class);
		
		// 응답받은 객체를 이용해서 파싱 
		JSONParser p = new JSONParser();
		JSONObject obj = (JSONObject) 
							p.parse(response.getBody());
		
		JSONObject reObj = (JSONObject) obj.get("response");
		
		String id = String.valueOf(reObj.get("id"));
		String email = String.valueOf(reObj.get("email"));
		String name = String.valueOf(reObj.get("name"));
		String mobile = String.valueOf(reObj.get("mobile"));
		
		
		
		return NaverDTO.builder()
					   .id(id)
					   .email(email)
					   .name(name)
					   .build();
		
	}
	
	
	
}
