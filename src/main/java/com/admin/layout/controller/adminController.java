package com.admin.layout.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.admin.layout.repository.itemRepository;
import com.admin.layout.service.adminService;
import com.admin.layout.vo.item;
import com.admin.layout.vo.Member;
import com.admin.layout.vo.Order;
import com.admin.layout.vo.category;

import lombok.extern.slf4j.Slf4j;

// 관리자 관련 컨트롤러 
@Slf4j
@Controller
public class adminController {
	
	@Autowired
	private adminService adminservice;
	

	@GetMapping("/admin")
	public String adminmain(Model model) {

		log.info("controller-adminmain()");

		return "admin/index";
	}
	
//	약에 대한 정보리스트
	@GetMapping("/admin/mediceninfo")
	public String medicenInfo(Model model) {
		log.info("controller-mediceninfo()");
		List<item> list = adminservice.printItem();
		log.info("admin은 잘 들어왔나?");
		model.addAttribute("lists", list);
		return "admin/mediceninfo";
	}
	
	
	
//	약에 대한 정보 삭제
	@GetMapping("/admin/delete")
	public String medicenDelete(Long itemNum) {
		log.info("Item deleteController" +itemNum);
		adminservice.deleteItem(itemNum);
		return"redirect:/admin/mediceninfo";
	}
	
	//약품 삽입폼 들어가기
	@GetMapping("/admin/productInsert")
	public String productInsertPage(Model model) {

		log.info("productInsertPage()");
		List<category> list = adminservice.printCat();
		model.addAttribute("lists",list);
		return "admin/productInsert";
	}
	
//	실제로 제품정보 입력
	@PostMapping("/admin/productInsert")
	public String productInsert(item item,HttpServletRequest request, @RequestPart("file") MultipartFile files) {
		log.info("productInsertPost");
		try {
			adminservice.itemjoin(item,request,files);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("확인!!"+item);
		return "redirect:/admin/productInsert";
	}
	
//	카테고리 정보리스트
	@GetMapping("/admin/categoryinsert")
	public String categoryinsert(Model model) {

		log.info("categorymapping()");
		List<category> list = adminservice.printCat();
		model.addAttribute("lists", list);
		return "admin/categoryinsert";
	}
	
//	카테고리 입력
	@PostMapping("/admin/categoryinsert")
	public String productInsert(category cat) {
		log.info("productInsertPost");
		adminservice.categoryjoin(cat);
		log.info("확인!!"+cat);
		return "redirect:/admin/categoryinsert";
	}
	
	
//	카테고리에 대한 정보 삭제
	@GetMapping("/admin/catdelete")
	public String catDelete(Long catNum) {
		log.info("Item deleteController" +catNum);
		adminservice.deleteCatItem(catNum);
		return"redirect:/admin/categoryinsert";
	}
	

//	주문정보확인
	@GetMapping("/admin/order")
	public String Order(Model model) {

		log.info("Order()");
		List<Order> orderlist = adminservice.printOrder();
		model.addAttribute("orderlists",orderlist);
		return "/admin/order";
	}
	
	
	// 주문정보변경
	@PostMapping("/admin/order/update")
	public String updateOrder(@RequestParam("orderNum") Long orderNum, @RequestParam("deliveryOption") String deliveryOption) {
	    log.info("updateOrder() called");
	    adminservice.updateshop(orderNum, deliveryOption);
	    return "redirect:/admin/order";
	}


	
	
//	멤버리스트 확인
	@GetMapping("/admin/memberInfo")
	public String memberInfo(Model model) {

		log.info("controller-mediceninfo()");
		List<Member> list = adminservice.printMember();
		log.info("memberInfo은 잘 들어왔나?");
		model.addAttribute("lists", list);
		return "admin/memberInfo";
	}
	
	
//	카테고리에 대한 정보 삭제
	@GetMapping("/admin/memdelete")
	public String MemDelete(Long memNum) {
		log.info("Item deleteController" +memNum);
		adminservice.deleteMember(memNum);
		return"redirect:/admin/memberInfo";
	}
	
	
}
