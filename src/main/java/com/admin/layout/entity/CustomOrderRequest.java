package com.admin.layout.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomOrderRequest {
    private Integer itemNum; // 상품 고유번호
    private Integer quantity; // 상품 수량
    private Integer productPrice; // 상품 가격
   
    private String memName; 

    
}


	
	
