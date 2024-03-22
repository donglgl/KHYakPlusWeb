package com.admin.layout.apilogin.DTO;



import com.admin.layout.vo.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDTO {
    private Integer orderDetailId;
    private Long orderNum;
    private Long itemNum;
    private String memName;
    private String detailName;
    private Integer detailPrice;
    private Integer detailCnt;
    private String detailImg;
    public OrderDetail toEntity() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderDetailId(this.orderDetailId);
        orderDetail.setOrderNum(this.orderNum);
        orderDetail.setItemNum(this.itemNum);
        orderDetail.setMemName(this.memName);
        orderDetail.setDetailName(this.detailName);
        orderDetail.setDetailPrice(this.detailPrice);
        orderDetail.setDetailCnt(this.detailCnt);
        orderDetail.setDetailImg(this.detailImg);
        return orderDetail;
    }

    }
