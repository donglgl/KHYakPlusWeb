package com.admin.layout.service;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.admin.layout.repository.itemRepository;
import com.admin.layout.repository.memberRepository;
import com.admin.layout.repository.orderRepository;
import com.admin.layout.vo.Member;
import com.admin.layout.vo.Order;

import groovy.util.logging.Slf4j;

@Slf4j
@Service
public class OrderService {

	@Autowired
	private memberRepository memberRepository;

//	김동현
	@Autowired
	private itemRepository itemRepository;

	@Autowired
	private orderRepository orderrepository;

	public void insertOrder(Long totalQuatity, Long totalPrice, String deliveryOption, String itemName, Model model, HttpSession session) {
		
		String id = (String)session.getAttribute("id");
		Member member = memberRepository.findByMemId(id);
		Order or = new Order();
		or.setMemName(id);
		or.setOrderAddr(member.getMemAddr());
		or.setOrderDetail(member.getDetailAddr());
		or.setOrderCheck("결제 대기");
		LocalDateTime now = LocalDateTime.now();
		or.setOrderDate(now);
		or.setOrderReq(deliveryOption);
		or.setOrderItem(itemName);
		or.setOrderPrice(Long.valueOf(totalPrice));
		or.setOrderPh(member.getMemPh());
		orderrepository.save(or);

	}
}
