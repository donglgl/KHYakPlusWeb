package com.admin.layout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.layout.entity.CustomOrderRequest;
import com.admin.layout.repository.itemRepository;
import com.admin.layout.repository.memberRepository;
import com.admin.layout.repository.orderRepository;
import com.admin.layout.service.OrderDetailService;
import com.admin.layout.service.OrderService;
import com.admin.layout.vo.Member;
import com.admin.layout.vo.Order;
import com.admin.layout.vo.OrderDetail;
import com.admin.layout.vo.item;

import lombok.extern.slf4j.Slf4j;

import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class PayAndMarketController {

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private memberRepository memberRepository;

	
//	김동현
	@Autowired
	private itemRepository itemRepository;

	
	
	@Autowired
	OrderService orderservice;
	
	
	@PostMapping("/saveOrder")
	@ResponseBody
	public ResponseEntity<String> saveOrder(@RequestBody CustomOrderRequest customOrderRequest) {
		try {
			// OrderDetail객체로 변환
			OrderDetail orderDetail = convertToOrderDetail(customOrderRequest);

			// 변환된 OrderDetail 객체를 데이터베이스에 저장
			orderDetailService.save(orderDetail);
			log.info("잘저장되었니? {}", orderDetail);
			return ResponseEntity.ok("주문이 성공적으로 저장되었습니다.");
		} catch (Exception e) {
			log.error("주문 저장 중 오류 발생: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 저장 중 오류가 발생했습니다.");
		}
	}

	// 주문요청을 저장하는 메서드
	private OrderDetail convertToOrderDetail(CustomOrderRequest customOrderRequest) {
		OrderDetail orderDetail = new OrderDetail();

		// 상품 고유번호 설정
		orderDetail.setItemNum(Long.valueOf(customOrderRequest.getItemNum()));
		// 상품 수량 설정
		orderDetail.setDetailCnt(customOrderRequest.getQuantity());
		// 상품 가격 설정
		orderDetail.setDetailPrice(customOrderRequest.getProductPrice());
		// 주문한 회원의 이름 설정
		orderDetail.setMemName(customOrderRequest.getMemName());

		return orderDetail;
	}

	@GetMapping("/orderDetail")
	public String OrderDetail(Model model, HttpSession session) {
		try {
			// 세션에서 사용자 정보를 안전하게 가져옵니다.
			String loginId = (String) session.getAttribute("login_id");
			String loginName = (String) session.getAttribute("login_name");
			String loginAddr = (String) session.getAttribute("login_addr");
			String loginPh = (String) session.getAttribute("login_ph");

			// 사용자의 주문 상세 정보를 가져옵니다.
			List<OrderDetail> orderDetails = orderDetailService.findByMemName(loginName);

			Integer totalQuantity = calculateTotalQuantity(orderDetails); // 총 수량 계산
			Integer totalPrice = calculateTotalPrice(orderDetails); // 총 가격 계산
			// 모델에 주문 상세 정보를 추가합니다.
			model.addAttribute("orderDetails", orderDetails);
			model.addAttribute("totalQuantity", totalQuantity);
			model.addAttribute("totalPrice", totalPrice);

			// 모델에 사용자 정보를 추가합니다.
			model.addAttribute("mem_id", loginId);
			model.addAttribute("mem_name", loginName);
			model.addAttribute("mem_addr", loginAddr);
			model.addAttribute("mem_ph", loginPh);
			log.info("모델에 추가된 총 수량: {}", model.getAttribute("totalQuantity"));
			log.info("모델에 추가된 총 가격: {}", model.getAttribute("totalPrice"));
			return "payAndmarket/pay";
		} catch (Exception e) {
			// 오류 처리 로직
			log.error("Error occurred while getting order details: {}", e.getMessage());
			return "error";
		}
	}
	
	
	

//    김동현 제작 구매버튼 클릭시 생성
	@GetMapping("/sel_Detail/singleOrder")
	public String singleOrder(@RequestParam("itemNum") int itemNum, @RequestParam("price") int price,
			@RequestParam("quantity") int quantity, Model model, HttpSession session) {
		log.info("여기까지는 오나?");
		Long totalPrice = Long.valueOf(quantity * price); // int 타입이었던 totalPrice를 long 타입으로 변환
		session.setAttribute("itemNum", itemNum);
		session.setAttribute("totalPrice", totalPrice); // totalPrice를 long 타입으로 저장
		session.setAttribute("totalQuantity", Long.valueOf(quantity)); // quantity를 long 타입으로 저장
		log.info("{},{},{}", itemNum, totalPrice, quantity);
		
		return "redirect:/payAndmarket/one";
	}

	@GetMapping("/payAndmarket/one")
	public String onepay(Model model, HttpSession session) {
//		구매 상품 단독상품 삽입
		log.info("onepay GetMapping");
		String id = (String) session.getAttribute("id");
		log.info("{}", id);
		Object totalQuantity = session.getAttribute("totalQuantity");
		Object itemNum = session.getAttribute("itemNum");
		Object totalPrice = session.getAttribute("totalPrice");
		log.info("{},{},{} 여기까지오면 절반으 왔다", itemNum, totalPrice, totalQuantity);
		String itemNum2 = String.valueOf(itemNum);
		log.info("{} 형변환이 불가능한건가?", itemNum2);
		item item = itemRepository.findByItemNum(Long.valueOf(itemNum2));
		model.addAttribute("orderDetails", item);
		model.addAttribute("totalPrice", String.valueOf(totalPrice));
		model.addAttribute("totalQuantity", String.valueOf(totalQuantity));
		log.info("{},{},{},{}", item, id, totalPrice, totalQuantity);
		
		
//      여기서 member 내용을 가지고 오자!
		Member member = memberRepository.findByMemId(id);
		model.addAttribute("Member",member);
		
		return "/payAndmarket/pay"; // 뷰 경로 수정
	}

//제작 완료	
	
//	결제 완료창 띄우기
	@PostMapping("/payAndmarket/one/pay")
	public String orderSubmit(@RequestParam("totalQuatity") Long totalQuatity,@RequestParam("totalPrice") Long totalPrice,
			@RequestParam("deliveryOption") String deliveryOption, @RequestParam("itemName")  String itemName,Model model, HttpSession session) {

		orderservice.insertOrder(totalQuatity,totalPrice,deliveryOption,itemName,model,session);
		return "redirect:/"; 
	}
	
	@GetMapping("/payAndmarket/one/pay/success")
	public String orderSuccess(){
		return "/payAndmarket/success";
	}

	
	
	private Integer calculateTotalQuantity(List<OrderDetail> orderDetails) {
		Integer totalQuantity = 0;
		for (OrderDetail orderDetail : orderDetails) {
			// 각 OrderDetail의 수량을 로깅
			log.info("OrderDetail 수량: {}", orderDetail.getDetailCnt());
			totalQuantity += orderDetail.getDetailCnt();
		}
		// 최종 총 수량 로깅
		log.info("총 수량: {}", totalQuantity);
		return totalQuantity;
	}

	private Integer calculateTotalPrice(List<OrderDetail> orderDetails) {
		Integer totalPrice = 0;
		for (OrderDetail orderDetail : orderDetails) {
			// 각 OrderDetail의 수량과 가격 로깅
			log.info("OrderDetail 수량: {}, 가격: {}", orderDetail.getDetailCnt(), orderDetail.getDetailPrice());
			totalPrice += orderDetail.getDetailCnt() * orderDetail.getDetailPrice();
		}
		// 최종 총 가격 로깅
		log.info("총 가격: {}", totalPrice);
		return totalPrice;
	}
}