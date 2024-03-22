package com.admin.layout.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.admin.layout.repository.memberRepository;
import com.admin.layout.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class userService {

	@Value("${com.example.upload.path}") // application.properties의 변수
	private String uploadPath;
	@Autowired
	memberRepository memberrepository;

	public boolean login(String memId, String memPwd) {

		Member member = memberrepository.findByMemIdAndMemPwd(memId, memPwd);

		boolean check = (member == null) ? false : true;

		return check;
	}
	
	//api로 로그인 했을 때 이메 확인하기 
	public Member login(String email) {
		
		return memberrepository.findByMemEmail(email);
		
	}

//	아이디 중복체크
	public boolean checkId(String memId) {

		return !memberrepository.existsByMemId(memId);

	}

	public void memberJoin(Member member,HttpServletRequest request, MultipartFile files) throws Exception{
		
	
		String sourceFileName = files.getOriginalFilename();
		log.info("{}",sourceFileName);
		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
		File destinationFile;
		String destinationFileName;
		String fileUrl = uploadPath;

		do {
			destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
			destinationFile = new File(fileUrl + destinationFileName);
		} while (destinationFile.exists());

		destinationFile.getParentFile().mkdirs();
		files.transferTo(destinationFile);
		member.setMemberImg(destinationFileName);
		member.setMemberImgName(sourceFileName);
		member.setMemberImgUrl(fileUrl);
		memberrepository.save(member);
		log.info("item join service 끝 !" + member);
	}

	public boolean checknickname(String nickname) {
		
		return !memberrepository.existsByMemNickName(nickname);
	}
	
	
	public Member getLoggedInUserData(String userId) {
	    // 사용자의 아이디로 회원 정보를 가져옴
	    return memberrepository.findByMemId(userId);
	}
	
	 @Transactional 
	public void deleteMemberById(String id) {
		memberrepository.deleteByMemId(id);
	
	 }
	
	

}
