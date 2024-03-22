package com.admin.layout.vo;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "totalOrder123124123")
@Data
public class TotalOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_Num")
    private Long orderNum;

    @ManyToOne
    @JoinColumn(name = "mem_num", nullable = false) // 여기서 외래키를 지정합니다.
    private Member member;

    @Column(name = "order_code")
    private Long orderCode;

    @Column(name = "order_detail")
    private String orderDetail;

    @Column(name = "order_item")
    private String orderItem;

    @Column(name = "order_price")
    private Long orderPrice;

    @Column(name = "order_date", nullable = false)
    @CreatedDate
    private LocalDateTime orderDate;

    @Column(name = "order_check")
    private String orderCheck;

    @Column(name = "order_exchange")
    private String orderExchange;
}