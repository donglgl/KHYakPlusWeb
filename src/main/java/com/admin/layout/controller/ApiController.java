package com.admin.layout.controller;



import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.admin.layout.vo.Pill;
import com.admin.layout.vo.Pill3;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import java.util.List;




@Slf4j
@Controller
public class ApiController {

	 @GetMapping("/detail/{itemSeq}")
	public String detailPage(@PathVariable String itemSeq, Model model) {
	    try {
	        // 첫 번째 API 호출
	        StringBuilder urlBuilder1 = new StringBuilder("http://apis.data.go.kr/1471000/MdcinGrnIdntfcInfoService01/getMdcinGrnIdntfcInfoList01");
	        urlBuilder1.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=1dx8Z%2B4bt4GPV%2BSy1Brh7elTQVEoTL14jl%2BfxrcNL71gRS8jkIF5fsScLvCKSGfpQ%2FQpS6dR%2F7scT5CwkKMOYQ%3D%3D"); /* 서비스키 */
	        urlBuilder1.append("&" + URLEncoder.encode("item_seq", "UTF-8") + "=" + URLEncoder.encode(itemSeq, "UTF-8")); /* 품목일련번호 */
	        urlBuilder1.append("&" + URLEncoder.encode("etc_otc_name", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 구분 */
	        urlBuilder1.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
	        urlBuilder1.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 한 페이지 결과 수 */

	        // URL 연결 및 XML 파싱
	        URL url1 = new URL(urlBuilder1.toString());
	        HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
	        conn1.setRequestMethod("GET");
	        conn1.connect();

	        DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder1 = factory1.newDocumentBuilder();
	        Document doc1 = builder1.parse(conn1.getInputStream());
	        NodeList nodeList1 = doc1.getElementsByTagName("item");
	        List<Pill> pillList1 = new ArrayList<>();
	        for (int i = 0; i < nodeList1.getLength(); i++) {
	            Element element = (Element) nodeList1.item(i);
	            Pill pill = new Pill();
	            // 데이터 추출 및 모델에 추가
	            pill.setItemImage(getTagValue("ITEM_IMAGE", element));
	            pill.setItemName(getTagValue("ITEM_NAME", element));
	            pill.setItemEngName(getTagValue("ITEM_ENG_NAME", element));
	            pill.setItemSeq(getTagValue("ITEM_SEQ", element));
	            pill.setEtcOtcName(getTagValue("ETC_OTC_NAME", element));
	            pill.setClassName(getTagValue("CLASS_NAME", element));
	            pill.setEntpName(getTagValue("ENTP_NAME", element));
	            pill.setPageNo(parseIntOrDefault(getTagValue("pageNo", element), -1));
	            pill.setNumOfRows(parseIntOrDefault(getTagValue("numOfRows", element), -1));
	            pillList1.add(pill);
	        }
	        model.addAttribute("list1", pillList1);

	        // 두 번째 API 호출
	        StringBuilder urlBuilder2 = new StringBuilder("https://apis.data.go.kr/1471000/DrugPrdtPrmsnInfoService05/getDrugPrdtPrmsnDtlInq04");
	        urlBuilder2.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=1dx8Z%2B4bt4GPV%2BSy1Brh7elTQVEoTL14jl%2BfxrcNL71gRS8jkIF5fsScLvCKSGfpQ%2FQpS6dR%2F7scT5CwkKMOYQ%3D%3D"); /* 서비스키 */
	        urlBuilder2.append("&" + URLEncoder.encode("ITEM_SEQ", "UTF-8") + "=" + URLEncoder.encode(itemSeq, "UTF-8")); /* 품목일련번호 */
	        urlBuilder2.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
	        urlBuilder2.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 한 페이지 결과 수 */

	        // URL 연결 및 XML 파싱
	        URL url2 = new URL(urlBuilder2.toString());
	        HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
	        conn2.setRequestMethod("GET");
	        conn2.connect();

	        DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder2 = factory2.newDocumentBuilder();
	        Document doc2 = builder2.parse(conn2.getInputStream());
	        NodeList nodeList2 = doc2.getElementsByTagName("item");
	        List<Pill3> pillList2 = new ArrayList<>();
	        for (int i = 0; i < nodeList2.getLength(); i++) {
	            Element element = (Element) nodeList2.item(i);
	            Pill3 pill3 = new Pill3();
	            // 데이터 추출 및 모델에 추가
	            pill3.setITEM_SEQ(getTagValue("ITEM_SEQ", element)); // itemSeq로 변경
	            pill3.setVALID_TERM(getTagValue("VALID_TERM", element)); // validTerm으로 변경
	            pill3.setEE_DOC_DATA(getTagValue("EE_DOC_DATA", element)); // eeDocData로 변경
	            pill3.setUD_DOC_DATA(getTagValue("UD_DOC_DATA", element)); // udDocData로 변경
	            pill3.setNB_DOC_DATA(getTagValue("NB_DOC_DATA", element)); // nbDocData로 변경
	            pill3.setMATERIAL_NAME(getTagValue("MATERIAL_NAME", element)); // materialName으로 변경
	            pill3.setPageNo(parseIntOrDefault(getTagValue("pageNo", element), -1)); // pageNo로 변경
	            pill3.setNumOfRows(parseIntOrDefault(getTagValue("numOfRows", element), -1)); // numOfRows로 변경
	            pillList2.add(pill3);
	            
	         
	        }
	        model.addAttribute("list2", pillList2);
	        
	        log.info("Pill List Size: {}", pillList2.size());
	      
	        return "/info/detail"; // 뷰 이름 반환
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("error", e.getMessage());
	        return "errorView"; // 오류 발생 시 오류 페이지로 이동
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
