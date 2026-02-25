package com.boot.json.controller;

import com.boot.json.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

	private final StoreService storeService;
	private final String storeName = "하이미디어";

	// 매장 페이지
	@GetMapping
	public String storePage(Model model) {

		int couponCount = storeService.getCouponCount(storeName);
		model.addAttribute("couponCount", couponCount);
		return "store";
	}

	// 차량 검색
	@GetMapping("/parking/search")
	public String searchParking(@RequestParam String carNo, Model model) {

		List<Map<String, Object>> records = storeService.searchCar(carNo);

		model.addAttribute("records", records);
		model.addAttribute("carNo", carNo);
		return "parkingRecords";
	}

	// 쿠폰 사용
	@PostMapping("/coupon/use")
	public String useCoupon(@RequestParam String carNo, RedirectAttributes ra) {

		int discount = storeService.applyCoupon(carNo);

		ra.addFlashAttribute("message", "쿠폰이 적용되었습니다. (" + discount + "원 할인)");

		return "redirect:/store";
	}
}
