package com.admin.layout.vo;

import java.util.Date; 

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "REPLY")
public class Reply {

	
	// 댓글 번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REPLY_NUM")
	private Integer replyNum; 

	// 게시글 번호
	@Column(name = "BOARD_NUM") // 바꿈
	private Integer boardNum; 

	// 관리자 번호
	@Column(name = "ADMIN_NUM")
	private Integer adminNum; 

	// 등록일
	@Column(name = "REPLY_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date replyDate;

	// 내용
	@Column(name = "REPLY_CONTENT")
	private String replyContent;

	
}

