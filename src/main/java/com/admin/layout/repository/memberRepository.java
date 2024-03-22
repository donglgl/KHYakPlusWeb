package com.admin.layout.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.layout.vo.Member;

public interface memberRepository extends JpaRepository<Member,Long> {
	

	Member findByMemIdAndMemPwd(String id,String pwd); //로그인
	
	Member findByMemId(String id);
	
	Member findByMemName(String name);
	
	boolean existsByMemId(String id); //중복 아이디 검사
	
	boolean existsByMemNickName(String nickname);
	
	Member findByMemEmail(String email); //카카오톡 로그인 , 네이버로그인
	
	void deleteByMemId(String id);
}
