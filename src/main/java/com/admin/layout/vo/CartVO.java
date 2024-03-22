package com.admin.layout.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Cart")
public class CartVO {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartNum;
	private String memId;
	private String itemName;
	private Long itemPrice;
	private Long count;
	
}
