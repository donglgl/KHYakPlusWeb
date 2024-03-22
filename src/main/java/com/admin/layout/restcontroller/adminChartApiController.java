package com.admin.layout.restcontroller;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.admin.layout.repository.itemRepository;
import com.admin.layout.repository.memberRepository;
import com.admin.layout.vo.Member;
import com.admin.layout.vo.item;
// admin page에 차트를 출력하기 위한 restController

@RestController
public class adminChartApiController {

	@Autowired
	private itemRepository itemRepository;

	@Autowired
	private memberRepository memberRepository;

	@GetMapping("/api/items")
	public List<Map<String, Object>> getItems() {
		List<item> itemList = itemRepository.findAll();
		List<Map<String, Object>> result = new ArrayList<>();

		for (item item : itemList) {
			Map<String, Object> itemMap = new HashMap<>();

			// 아이템 번호(Long)를 문자열(String)로 변환하여 추가
			itemMap.put("itemNum", String.valueOf(item.getItemNum()));

			// 카테고리 이름(Long)을 문자열(String)로 변환하여 추가
			itemMap.put("catName", String.valueOf(item.getCatName()));

			// 나머지 item의 필드들을 itemMap에 추가
			itemMap.put("itemName", item.getItemName());
			// itemOldcnt와 itemCnt를 사용하여 차이를 계산하여 itemMap에 추가
			Long oldCnt = Long.parseLong(item.getItemOldcnt());
			Long cnt = Long.parseLong(item.getItemCnt());
			Long difference = oldCnt - cnt;
			itemMap.put("difference", difference);
			result.add(itemMap);
		}

		return result;
	}

	@GetMapping("/api/agechart")
	public Map<String, Integer> getAgechart() {
		List<Member> members  = memberRepository.findAll();
		Map<String, Integer> ageStatistics = new HashMap<>();

		// 연령대별로 초기화
		ageStatistics.put("10대", 0);
		ageStatistics.put("20대", 0);
		ageStatistics.put("30대", 0);
		ageStatistics.put("40대", 0);
		ageStatistics.put("50대 이상", 0);
		for (Member member : members) {
			String dateString = member.getMemBirthday();
			LocalDate birthDate = LocalDate.parse(dateString);
			LocalDate currentDate = LocalDate.now();
			Period period = Period.between(birthDate, currentDate);
			int age = period.getYears();

			// 연령대에 따라 통계 업데이트
			if (age < 20) {
				ageStatistics.put("10대", ageStatistics.get("10대") + 1);
			} else if (age >= 20 && age < 30) {
				ageStatistics.put("20대", ageStatistics.get("20대") + 1);
			} else if (age >= 30 && age < 40) {
				ageStatistics.put("30대", ageStatistics.get("30대") + 1);
			} else if (age >= 40 && age < 50) {
				ageStatistics.put("40대", ageStatistics.get("40대") + 1);
			} else {
				ageStatistics.put("50대 이상", ageStatistics.get("50대 이상") + 1);
			}
		}

		return ageStatistics;
	}
}
