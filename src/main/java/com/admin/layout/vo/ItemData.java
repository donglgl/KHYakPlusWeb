package com.admin.layout.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemData {
	
	private Date endDate;
	private int count;
	private String itemCategory;
	private String itemName;
	private String packagingUnit;
	private Date registerDate;
	
	
	
}
