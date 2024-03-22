package com.admin.layout.vo;

import java.util.Date; 

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "BOARD")
public class Board {

	// 게시글 번호 (반드시 entity에 존재)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOARD_NUM")
	private Integer boardNum;

	// 사용자 번호
	@Column(name = "MEM_NUM")
	private Integer memNum;

	// 사용자 아이디 (글쓴이) 
	@Column(name = "MEM_ID")
	private String memId;
		
	// 게시글 카테고리
	@Column(name = "BOARD_CATE")
	private String boardCate;

	// 제목
	@Column(name = "BOARD_TITLE")
	private String boardTitle;

	// 등록일 (작성일)
	@Column(name = "BOARD_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date boardDate;

	// 내용
	@Column(name = "BOARD_CONTENT")
	private String boardContent;
	
	// 답변완료(답변여부)
	@Column(name = "BOARD_CHECK")
	private String boardCheck = "답변대기";
}


