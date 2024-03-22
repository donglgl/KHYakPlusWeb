package com.admin.layout.vo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "totalOrder")
public class Order {

	@Id
	@Column(name = "order_Num")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderNum;
	@Column(name = "mem_name")
	private String memName;
	@Column(name = "order_addr")
	private String orderAddr;
	@Column(name = "order_detail")
	private String orderDetail;
	@Column(name = "order_ph")
	private String orderPh;
	@Column(name = "order_req")
	private String orderReq;
	@Column(name = "order_item")
	private String orderItem;
	@Column(name = "order_price")
	private Long orderPrice;
	@CreatedDate
	@Column(name = "order_date")
	private LocalDateTime orderDate;
	@Column(name = "order_check")
	private String orderCheck;

}
