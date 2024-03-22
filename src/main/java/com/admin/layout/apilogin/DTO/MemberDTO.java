package com.admin.layout.apilogin.DTO;

import java.util.Date;

import com.admin.layout.vo.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class MemberDTO {

	private Long num;
	@NonNull
	private String id;
	private String pwd;
	@NonNull
	private String name;
	private String ph;
	@NonNull
	private String address;
	private String birthday;
	private String email;
	private String nickname;
	private String pro; // 성별?

	private String MemberImg;
	private String MemberImgName;
	private String MemberImgUrl;

	
}
