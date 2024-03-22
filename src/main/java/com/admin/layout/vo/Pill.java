package com.admin.layout.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pill {
  //낱알

	private String itemName;   //품목명(제품명)
    private String itemEngName;  //영문이름
    private String itemSeq;  // 품목일련번호 Integer로 변경
    private String entpName;  //업체명(제조사)
    private String className;  //분류
    private String itemImage;  //이미지
    private String etcOtcName;  //구분
    private Integer pageNo; // 페이지번호 Integer로 변경
    private Integer numOfRows; // 페이지결과수 Integer로 변경
    private String drugShape; //모양
    private String colorClass; //색깔
    private String formCodeName; //제형
    private Integer itemPermitDate; //품목허가일자
  
    


}