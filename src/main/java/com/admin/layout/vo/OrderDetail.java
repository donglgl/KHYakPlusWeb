package com.admin.layout.vo;

import javax.persistence.*;

import com.admin.layout.apilogin.DTO.OrderDetailDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Integer orderDetailId;



    @Column(name = "order_num", nullable=false)
    private Long orderNum ;

    @Column(name = "item_num", nullable = false)
    private Long itemNum;

    @Column(name = "mem_name", nullable = false)
    private String memName;

    @Column(name = "mem_num", nullable = false)
    private Long memNum;

    @Column(name = "detail_name", nullable = false)
    private String detailName;

    @Column(name = "detail_price", nullable = false)
    private Integer detailPrice;

    @Column(name = "detail_cnt", nullable = false)
    private Integer detailCnt;

    @Column(name = "detail_img", nullable = false)
    private String detailImg;

    // 생성자
    public OrderDetail(Long itemNum, String memName, Long memNum, String detailName, Integer detailPrice, Integer detailCnt, String detailImg) {
        this.itemNum = itemNum;
        this.memName = memName;
        this.memNum = memNum;
        this.detailName = detailName;
        this.detailPrice = detailPrice;
        this.detailCnt = detailCnt;
        this.detailImg = detailImg;
    }
    
    // DTO로 변환하는 메서드
    public OrderDetailDTO toDto() {
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setOrderNum(this.orderNum); // 주문 번호 설정
        dto.setItemNum(this.itemNum);
        dto.setDetailCnt(this.detailCnt);
        dto.setDetailName(this.detailName);
        dto.setDetailPrice(this.detailPrice);
        dto.setDetailImg(this.detailImg);
        return dto;
    }
}