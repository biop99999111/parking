package com.boot.json.controller;

import com.boot.json.model.Car;
import com.boot.json.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor // final이 붙은 필드를 자동으로 주입해줍니다.
@RequestMapping("/store")
public class StoreController {
	
    // final을 붙여서 안전하게 주입받습니다. @Autowired는 생략 가능합니다.
    private final StoreService storeService;
	
    private String storeName = "하이미디어";

    // 매장 페이지
    @GetMapping
    public String storePage(Model model) {
        int couponCount = storeService.getCouponCount(storeName);
        model.addAttribute("couponCount", couponCount);
        return "store";
    }

    // 차량 검색
    // @RequestParam("carNo") 처럼 이름을 명시해야 500 에러가 나지 않습니다.
    @GetMapping("/parking/search")
    public String searchParking(@RequestParam("carNo") String carNo, Model model) {

        // 서비스에서 반환하는 타입(Car)에 맞춰서 받습니다.
        Car records = storeService.searchCar(carNo);

        model.addAttribute("records", records);
        model.addAttribute("carNo", carNo);
        return "parkingRecords";
    }

    // 쿠폰 사용
    @PostMapping("/coupon/use")
    public String useCoupon(@RequestParam("carNo") String carNo, RedirectAttributes ra) {

        int discount = storeService.applyCoupon(carNo);
        
        

        ra.addFlashAttribute("message", "쿠폰이 적용되었습니다. (" + discount + "원 할인)");

        return "redirect:/store";
    }
}