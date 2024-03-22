package com.admin.layout.vo;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Table(name = "sign_member")
public class Member {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memNum;
	private String memId;
	private String memEmail;
	private String memPwd;
	private String memNickName;// 별명
	private String memName; // 실제이름
	private String memBirthday;
	private String memGen;
	// 이미지 관련처리
	private String MemberImg;
	private String MemberImgName;
	private String MemberImgUrl;
	@Column(name = "mem_ph")
	private String memPh;
	
	private String memAddr; //우편주소/기본주소 합
	private String detailAddr; //상세주소




	
}
