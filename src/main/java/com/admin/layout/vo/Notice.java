package com.admin.layout.vo;

import java.util.Date;  

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "NOTICE")
public class Notice {

	// 공지 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTICE_NUM")
    private Integer noticeNum;

    // 관리자 번호
    @Column(name = "ADMIN_NUM")
    private Integer adminNum;

    // 관리자 아이디
    @Column(name = "ADMIN_ID")
    private String adminId;

    // 공지 카테고리
    @Column(name = "NOTICE_CATE")
    private String noticeCate;

    // 제목
    @Column(name = "NOTICE_TITLE")
    private String noticeTitle;

    // 등록일 (작성일)
    @Column(name = "NOTICE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date noticeDate;

    // 내용
    @Column(name = "NOTICE_CONTENT")
    private String noticeContent;

    // 조회수
    @Column(name = "NOTICE_HIT")
    private int noticeHit;
}


