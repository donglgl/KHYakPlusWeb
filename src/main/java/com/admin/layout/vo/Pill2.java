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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="PILL2")
public class Pill2 {
	//e약

		
	    @Id
	    @Column(name = "ITEM_IMG")
	    private String itemImage;

	    @Column(name="ITEEM_NAME")
	    private String itemName;

	    @Column(name="ENTP_NAME")
	    private String entpName;

	    @Column(name="EFCY_QESITM")
	    private String efcyQesitm;

	    @Column(name="USE_METHOD_QESITM")
	    private String useMethodQesitm;

	    @Column(name="ATPNWARNQESITM")
	    private String atpnWarnQesitm;

	    @Column(name="ATPN_QESITM") // 추가된 필드에 @Column 어노테이션 추가
	    private String atpnQesitm;

	    @Column(name="INTRC_QESITM") // 추가된 필드에 @Column 어노테이션 추가
	    private String intrcQesitm;

	    @Column(name="SE_QESITM") // 추가된 필드에 @Column 어노테이션 추가
	    private String seQesitm;

	    @Column(name="DEPOSIT_METHOD_QESITM") // 추가된 필드에 @Column 어노테이션 추가
	    private String depositMethodQesitm;

	    // 추가된 필드
	    @Column(name="ITEM_SEQ")
	    private String itemSeq;

	    @Column(name="PAGE_NO")
	    private Integer pageNo;

	    @Column(name="NUM_OF_ROWS")
	    private Integer numOfRows;

	    // 생성자, 게터, 세터 등 필요한 메서드 추가
	}



