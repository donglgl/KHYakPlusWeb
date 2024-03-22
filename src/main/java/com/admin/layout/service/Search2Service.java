package com.admin.layout.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.admin.layout.vo.Pill2;



@Service
public class Search2Service {

    public static ArrayList<Pill2> getPillList(String symptom) {
        ArrayList<Pill2> list = new ArrayList<>();

        // API 호출을 위한 필요한 변수들 선언
        String serviceKey = "1dx8Z%2B4bt4GPV%2BSy1Brh7elTQVEoTL14jl%2BfxrcNL71gRS8jkIF5fsScLvCKSGfpQ%2FQpS6dR%2F7scT5CwkKMOYQ%3D%3D";
        int numOfRows = 10;
        String useMethodQesitm = symptom;
      
        String type = "json";

        try {
            // URL 조립
            StringBuilder sb = new StringBuilder();
            sb.append("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?");
            sb.append("serviceKey=" + serviceKey);
            sb.append("&numOfRows=" + numOfRows);
            sb.append("&efcyQesitm=" + URLEncoder.encode(useMethodQesitm, "UTF-8"));
        
        
            sb.append("&type=" + type);

            // URL로 연결
            URL url = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 응답 코드 확인
            System.out.println(sb.toString());
            System.out.println(conn.getResponseCode());

            // 응답 데이터 읽기
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder newResult = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                newResult.append(line);
            }
            rd.close();
            conn.disconnect();

            // JSON 파싱
            JSONParser jp = new JSONParser();
            JSONObject job = (JSONObject) jp.parse(newResult.toString());
            JSONArray array = (JSONArray) ((JSONObject) job.get("body")).get("items");

            // 약물 정보 파싱 및 객체 생성
            for (int i = 0; i < array.size(); i++) {
                JSONObject temp = (JSONObject) array.get(i);
                String itemImage = (String) temp.get("itemImage"); // 이미지
                String itemName1 = (String) temp.get("itemName"); // 품목명
                String entpName = (String) temp.get("entpName"); // 업체명
                String useMethodQesitm1 = (String) temp.get("useMethodQesitm"); // 사용방법
                String efcyQesitm = (String) temp.get("efcyQesitm"); // 효과
                String intrcQesitm = (String) temp.get("intrcQesitm"); // 상호작용
                String seQesitm = (String) temp.get("seQesitm"); // 부작용
                String depositMethodQesitm = (String) temp.get("depositMethodQesitm"); // 보관법
                String atpnWarnQesitm = (String) temp.get("atpnWarnQesitm"); 
                String atpnQesitm = (String) temp.get("atpnQesitm"); // 보관법

                
                String itemSeq = null;
                if (temp.get("itemSeq") != null) {
                    itemSeq =(String) temp.get("itemSeq");
                }
                Integer pageNo = null;
                if (temp.get("pageNo") != null) {
                	pageNo = Integer.parseInt((String) temp.get("pageNo"));
                }
                Integer numOfRows1 = null;
                if (temp.get("numOfRows") != null) {
                	numOfRows1 = Integer.parseInt((String) temp.get("numOfRows"));
                }


                
                // 약물 객체 생성 및 리스트에 추가
                Pill2 m = new Pill2(itemImage, itemName1, entpName, useMethodQesitm1, efcyQesitm, intrcQesitm,
                        seQesitm, depositMethodQesitm, atpnWarnQesitm, atpnQesitm,itemSeq,numOfRows1,pageNo);
                list.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 약물 정보 리스트 반환
        return list;
    }
}
