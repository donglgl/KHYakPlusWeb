package com.admin.layout.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.admin.layout.repository.categoryRepository;
import com.admin.layout.repository.itemRepository;
import com.admin.layout.repository.memberRepository;
import com.admin.layout.repository.orderRepository;
import com.admin.layout.vo.Member;
import com.admin.layout.vo.Order;
import com.admin.layout.vo.category;
import com.admin.layout.vo.item;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class adminService {

	@Value("${com.example.upload.path}") // application.properties의 변수
	private String uploadPath;

	@Autowired
	private itemRepository itemrepository;

	@Autowired
	private orderRepository orderrepository;

	@Autowired
	private memberRepository memberrepository;

	@Autowired
	private categoryRepository categoryrepository;

//	관리자페이지 item 출력
	public List<item> printItem() {
		log.info("service printItem");

		List<item> list = itemrepository.findAll();
		log.info("{}", list);
		return list;
	}



//	관리자페이지 아이템 삽입
	public void itemjoin(item item, HttpServletRequest request, MultipartFile files) throws Exception {
		log.info("item join service 시작");
		item.setItemOldcnt(item.getItemCnt());
		String sourceFileName = files.getOriginalFilename();
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
		item.setItemImg(destinationFileName);
		item.setItemImgName(sourceFileName);
		item.setItemImgurl(fileUrl);
		log.info("item join service 끝 !" + item);
		itemrepository.save(item);
	}

//	아이템 삭제

	public void deleteItem(Long itemNum) {
		itemrepository.deleteById(itemNum);

		log.info("item 삭제완료 adminService");

	}

//	멤버 출력
	public List<Member> printMember() {
		List<Member> list = memberrepository.findAll();

		log.info("{}", list);
		return list;
	}

//	멤버 삭제
	public void deleteMember(Long memNum) {
		log.info("memNum 삭제시도 deleteMember");
		memberrepository.deleteById(memNum);
		log.info("memNum 삭제완료 deleteMember");

	}

// 카테고리 출력
	public List<category> printCat() {
		List<category> list = categoryrepository.findAll();
		return list;
	}

//	카테고리 아이템 삽입
	public void categoryjoin(category category) {

		log.info("category join service" + category);
		categoryrepository.save(category);
	}

//	카테고리삭제
	public void deleteCatItem(Long catNum) {
		categoryrepository.deleteById(catNum);
		log.info("catNum 삭제완료 deleteCatItem");

	}
	
	
//	관리자페이지 주문 출력
	public List<Order> printOrder() {

		log.info("service printOrder");
		List<Order> list = orderrepository.findAll();
		log.info("{}", list);
		return list;
	}
	
//	관리자페이지 주문상태 변경
	public void updateshop(Long orderNum,String orderCheck) {
		log.info("updateshop");
		Order order = orderrepository.findByOrderNum(orderNum);
		order.setOrderCheck(orderCheck);
		orderrepository.save(order);
	}

}
