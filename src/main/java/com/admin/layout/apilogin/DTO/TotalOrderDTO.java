package com.admin.layout.apilogin.DTO;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TotalOrderDTO {

    private Long orderNum;
    private Long memNum;
    private Long orderCode;
   
    private String orderDetail;
  
    private String orderItem;
    private Long orderPrice;
    private LocalDateTime orderDate;
    private String orderCheck;
    private String orderExchange; 

  
}