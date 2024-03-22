package com.admin.layout.vo;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "CATEGORY")
public class category {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cat_num")
	private Long catNum;
	@Column(name="cat_name")
	private String catName;
}
