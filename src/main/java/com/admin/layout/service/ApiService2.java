package com.admin.layout.service;


import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.admin.layout.vo.Pill;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ApiService2 {
	
	
	
	
	//전체 검색 서비스
	
	   public List<Pill> searchPillsByAll(String all) {
	        List<Pill> pillList = new ArrayList<>();
	        try {
	            // 검색어를 분리하여 제품명과 제조사명으로 나누기
	            String[] keywords = all.split("\\s+");
	            String title = keywords[0];
	            String ent = (keywords.length > 1) ? keywords[1] : null;

	            // 제품명과 제조사명을 각각 검색하여 약물 정보를 가져오기
	            List<Pill> titleResult = searchPills(title, null, 1, 25);
	            List<Pill> entResult = (ent != null) ? searchPills(null, ent, 1, 25) : new ArrayList<>();

	            // 중복을 제거한 약물 정보 리스트를 생성
	            Set<Pill> uniquePills = new HashSet<>();
	            uniquePills.addAll(titleResult);
	            uniquePills.addAll(entResult);

	            // 중복을 제거한 결과를 리스트에 저장
	            pillList.addAll(uniquePills);
	        } catch (Exception e) {
	            // 예외 처리
	            e.printStackTrace();
	        }
	        return pillList;
	    }
		
	
	  
	


	private List<Pill> searchPills(String itemName, String entpName, int pageNo, int numOfRows) {
	    List<Pill> pillList = new ArrayList<>(); // 약물 정보를 담을 리스트 생성
	    try {
	        // API 호출을 위한 URL 설정
	        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/MdcinGrnIdntfcInfoService01/getMdcinGrnIdntfcInfoList01");
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=1dx8Z%2B4bt4GPV%2BSy1Brh7elTQVEoTL14jl%2BfxrcNL71gRS8jkIF5fsScLvCKSGfpQ%2FQpS6dR%2F7scT5CwkKMOYQ%3D%3D"); /* 서비스키 */
	        urlBuilder.append("&" + URLEncoder.encode("item_image", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /* 이미지 */
	        
	        if (itemName != null) {
	            urlBuilder.append("&" + URLEncoder.encode("item_name", "UTF-8") + "=" + URLEncoder.encode(itemName, "UTF-8")); /* 제품명 */
	        }
	        if (entpName != null) {
	            urlBuilder.append("&" + URLEncoder.encode("entp_name", "UTF-8") + "=" + URLEncoder.encode(entpName, "UTF-8")); /* 업체명 */
	        }
	        
	        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pageNo), "UTF-8")); /* 페이지번호 */
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(numOfRows), "UTF-8")); /* 한 페이지 결과 수 */

	        // URL 연결 및 API 호출
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");

	        // 연결 확인
	        conn.connect();
	        System.out.println(urlBuilder.toString());
	        System.out.println(conn.getResponseCode());

	        // API 응답을 파싱하여 데이터 추출
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document doc = builder.parse(conn.getInputStream());
	        NodeList nodeList = doc.getElementsByTagName("item");

	        // 파싱한 데이터를 Pill 객체로 변환하여 리스트에 추가
	        for (int i = 0; i < nodeList.getLength(); i++) {
	            Element element = (Element) nodeList.item(i);
	            Pill pill = new Pill();
	            // 데이터 추출 및 모델에 추가
	            pill.setItemImage(getTagValue("ITEM_IMAGE", element));
	            pill.setItemName(getTagValue("ITEM_NAME", element));
	            pill.setEntpName(getTagValue("ENTP_NAME", element));
	            pill.setItemSeq(getTagValue("ITEM_SEQ", element));
	            pill.setDrugShape(getTagValue("DRUG_SHAPE", element));
	            pill.setColorClass(getTagValue("COLOR_CLASS1", element));
	            pill.setFormCodeName(getTagValue("FORM_CODE_NAME", element));
	            pill.setEtcOtcName(getTagValue("ETC_OTC_NAME", element));
	            pill.setItemPermitDate(parseIntOrDefault(getTagValue("ITEM_PERMIT_DATE", element), -1));
	            pill.setPageNo(parseIntOrDefault(getTagValue("pageNo", element), -1));
	            pill.setNumOfRows(parseIntOrDefault(getTagValue("numOfRows", element), -1));
	            pillList.add(pill);
	        }
	    } catch (Exception e) {
	        // 예외 발생 시 오류 메시지 출력
	        e.printStackTrace();
	    }
	    // 완성된 약물 정보 리스트 반환
	    return pillList;
	}



	//제품명 검색  혹은 제조사 검색

	public List<Pill> getResultList(Model model,@RequestParam(value = "title", required = false) String title ,
 											@RequestParam(value = "ent", required = false) String ent,
 											@RequestParam(value = "pageNo", required = false) int pageNo) {
     List<Pill> pillList = new ArrayList<>();
     try {
         // 외부 API와 통신하여 약물 정보를 가져오는 메서드 호출
         pillList = searchPills(title, ent, "", "", "", "", "",pageNo , 25);

         // 모델에 결과 추가
         model.addAttribute("list", pillList);
     } catch (Exception e) {
         e.printStackTrace();
         // 예외 발생 시 오류 메시지를 모델에 추가하여 뷰로 전달
         model.addAttribute("error", e.getMessage());
     }

     return pillList;
 }

 
 
 
 // 외부 API와 통신하여 약물 정보를 가져오는 메서드
 public List<Pill> searchPills(String itemName, String entpName, String drugShape, String colorClass1, String formCodeName, String etcOtcName, String itemPermitDate, int pageNo, int numOfRows) {
     List<Pill> pillList = new ArrayList<>(); // 약물 정보를 담을 리스트 생성
     try {
         // API 호출을 위한 URL 설정
         StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/MdcinGrnIdntfcInfoService01/getMdcinGrnIdntfcInfoList01");
         urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=1dx8Z%2B4bt4GPV%2BSy1Brh7elTQVEoTL14jl%2BfxrcNL71gRS8jkIF5fsScLvCKSGfpQ%2FQpS6dR%2F7scT5CwkKMOYQ%3D%3D"); /* 서비스키 */
         urlBuilder.append("&" + URLEncoder.encode("item_image", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /* 이미지 */

         if (itemName != null) {
             urlBuilder.append("&" + URLEncoder.encode("item_name", "UTF-8") + "=" + URLEncoder.encode(itemName, "UTF-8")); /* 제품명 */
         }
         if (entpName != null) {
             urlBuilder.append("&" + URLEncoder.encode("entp_name", "UTF-8") + "=" + URLEncoder.encode(entpName, "UTF-8")); /* 업체명 */
         }

         urlBuilder.append("&" + URLEncoder.encode("item_seq", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 품목일련번호 */
         urlBuilder.append("&" + URLEncoder.encode("drug_shape", "UTF-8") + "=" + URLEncoder.encode(drugShape, "UTF-8")); /*모양*/
         urlBuilder.append("&" + URLEncoder.encode("color_class1", "UTF-8") + "=" + URLEncoder.encode(colorClass1, "UTF-8")); /* 색깔*/
         urlBuilder.append("&" + URLEncoder.encode("form_code_name", "UTF-8") + "=" + URLEncoder.encode(formCodeName, "UTF-8")); /* 제형*/
         urlBuilder.append("&" + URLEncoder.encode("etc_otc_name", "UTF-8") + "=" + URLEncoder.encode(etcOtcName, "UTF-8")); /* 구분 */
         urlBuilder.append("&" + URLEncoder.encode("item_permit_dat", "UTF-8") + "=" + URLEncoder.encode(itemPermitDate, "UTF-8")); /* 품목허기일자*/
         urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pageNo), "UTF-8")); /* 페이지번호 */
         urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(numOfRows), "UTF-8")); /* 한 페이지 결과 수 */

         // URL 연결 및 API 호출
         URL url = new URL(urlBuilder.toString());
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");

         // 연결 확인
         conn.connect();
         System.out.println(urlBuilder.toString());
         System.out.println(conn.getResponseCode());

         // API 응답을 파싱하여 데이터 추출
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document doc = builder.parse(conn.getInputStream());
         NodeList nodeList = doc.getElementsByTagName("item");

         // 파싱한 데이터를 Pill 객체로 변환하여 리스트에 추가
         for (int i = 0; i < nodeList.getLength(); i++) {
             Element element = (Element) nodeList.item(i);
             Pill pill = new Pill();
             // 데이터 추출 및 모델에 추가
             pill.setItemImage(getTagValue("ITEM_IMAGE", element));
             pill.setItemName(getTagValue("ITEM_NAME", element));
             pill.setEntpName(getTagValue("ENTP_NAME", element));
             pill.setItemSeq(getTagValue("ITEM_SEQ", element));
             pill.setDrugShape(getTagValue("DRUG_SHAPE", element));
             pill.setColorClass(getTagValue("COLOR_CLASS1", element));
             pill.setFormCodeName(getTagValue("FORM_CODE_NAME", element));
             pill.setEtcOtcName(getTagValue("ETC_OTC_NAME", element));
             pill.setItemPermitDate(parseIntOrDefault(getTagValue("ITEM_PERMIT_DATE", element), -1));
             pill.setPageNo(parseIntOrDefault(getTagValue("pageNo", element), -1));
             pill.setNumOfRows(parseIntOrDefault(getTagValue("numOfRows", element), -1));
             pillList.add(pill);
         }
     } catch (Exception e) {
         // 예외 발생 시 오류 메시지 출력
         e.printStackTrace();
     }
     // 완성된 약물 정보 리스트 반환
     return pillList;
 }

 public int getTotalCount(String itemName, String entpName, String drugShape, String colorClass1, String formCodeName, String etcOtcName, String itemPermitDate, int pageNo, int numOfRows) {
     try {
         // API 호출을 위한 URL 설정
         StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/MdcinGrnIdntfcInfoService01/getMdcinGrnIdntfcInfoList01");
         urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=1dx8Z%2B4bt4GPV%2BSy1Brh7elTQVEoTL14jl%2BfxrcNL71gRS8jkIF5fsScLvCKSGfpQ%2FQpS6dR%2F7scT5CwkKMOYQ%3D%3D"); /* 서비스키 */
         urlBuilder.append("&" + URLEncoder.encode("item_image", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /* 이미지 */
         urlBuilder.append("&" + URLEncoder.encode("item_name", "UTF-8") + "=" + URLEncoder.encode(itemName != null ? itemName : "", "UTF-8")); /* 제품명 */
         urlBuilder.append("&" + URLEncoder.encode("entp_name", "UTF-8") + "=" + URLEncoder.encode(entpName != null ? entpName : "", "UTF-8")); /* 업체명 *//* 업체명 */
         urlBuilder.append("&" + URLEncoder.encode("item_seq", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 품목일련번호 */
         urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pageNo), "UTF-8")); /* 페이지번호 */
         urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(numOfRows), "UTF-8")); /* 한 페이지 결과 수 */

         // URL 연결 및 API 호출
         URL url = new URL(urlBuilder.toString());
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");

         // 연결 확인
         conn.connect();
         System.out.println(urlBuilder.toString());
         System.out.println(conn.getResponseCode());

         // API 응답을 파싱하여 데이터 추출
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document doc = builder.parse(conn.getInputStream());
         NodeList totalCountNodeList = doc.getElementsByTagName("totalCount");
         int totalCount = 0;
         if (totalCountNodeList.getLength() > 0) {
             totalCount = Integer.parseInt(totalCountNodeList.item(0).getTextContent());
         }
         return totalCount;
     } catch (Exception e) {
         // 예외 발생 시 로그를 출력하고 0을 반환합니다.
         e.printStackTrace();
         return 0;
     }
 }


 // XML 요소의 값을 가져오는 메서드
 private String getTagValue(String tag, Element element) {
     NodeList nodeList = element.getElementsByTagName(tag);
     if (nodeList.getLength() > 0) {
         return nodeList.item(0).getTextContent();
     } else {
         return ""; // 또는 다른 기본값을 반환할 수 있습니다.
     }
 }

 // 문자열을 정수로 변환하는 메서드
 private int parseIntOrDefault(String str, int defaultValue) {
     try {
         return Integer.parseInt(str);
     } catch (NumberFormatException e) {
         return defaultValue;
     }
 }



	}

