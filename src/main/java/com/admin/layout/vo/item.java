package com.admin.layout.vo;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "item")
public class item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_num")
	private Long itemNum;
	@Column(name = "cat_name")
	private String catName;
	@Column(name = "cat_subname")
	private String catSubName;
	@Column(name = "cat_subname2")
	private String catSubName2;
	@Column(name = "item_name")
	private String itemName; 
	
	@Column(name = "item_img") //저장할떄 이름
	private String itemImg;
	@Column(name = "item_imgname") //받아올 떄 찐 파일 이름
	private String  itemImgName;
	@Column(name = "item_imgurl") //저장 및 불러올 경로
	private String  itemImgurl;
	@Column(name = "item_content")
	private String itemContent;
	@Column(name = "item_price")
	private Long itemPrice;
	@Column(name = "item_cnt")
	private String itemCnt; // 남은 수량
	@Column(name = "item_oldcnt")
	private String itemOldcnt; // 초기 수량 인기상품 구별을 위한 db
	@Column(name = "item_fun")
	private String itemFun; // 기능
	@Column(name = "item_today")
	@ColumnDefault("1")
	private String itemToday; // 복용수량
	@Column(name = "item_eng")
	private String itemEng;
	@Column(name = "item_type")
	private String itemType;
	
	
	
	
}
