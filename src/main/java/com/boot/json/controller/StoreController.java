package com.boot.json.controller;

import com.boot.json.model.Car;

import com.boot.json.service.StoreService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
	
    // final 키워드를 붙여야 @RequiredArgsConstructor가 생성자 주입을 해줍니다.
    private final StoreService storeService;
    private final com.boot.json.model.CarMapper carMapper;   // 추가: Mapper 주입
    private final com.boot.json.service.CarService carService; // 추가: Service 주입
	
    private String storeName = "하이미디어";

    // 매장 페이지
    @GetMapping("/store")
    public String storePage(Model model) {
        int couponCount = storeService.getCouponCount(storeName);
        model.addAttribute("couponCount", couponCount);
        return "store";
    }
    
    @GetMapping("/parking/search")
    public String searchParking(@RequestParam("carNo") String carNo, Model model) {
        // 1. 매장 정보(쿠폰 수량) 조회
        int couponCount = storeService.getCouponCount(storeName);
        
        // 2. 여러 대 검색 (List로 받기)
        List<Car> carList = carMapper.selectCarByNo(carNo); 

        // 3. 검색된 모든 차량의 현재 요금 실시간 계산
        if (carList != null) {
            for (Car car : carList) {
                car.setExitTime(java.time.LocalDateTime.now());
                carService.calculateParkingFee(car); // 개별 차량 fee 필드 채움
            }
        }

        model.addAttribute("carList", carList); // 단건(carInfo)이 아닌 리스트(carList) 전달
        model.addAttribute("couponCount", couponCount);
        model.addAttribute("carNo", carNo);
        
        return "store";
    }

    @PostMapping("/coupon/use")
    public String useCoupon(@RequestParam("carNo") String carNo, RedirectAttributes ra) {
        // 1. 서비스 호출하여 쿠폰 적용 (보유량 감소 + 차량 할인 적용)
        // storeService.applyCoupon(carNo)는 이전에 작성한 로직입니다.
        int discount = storeService.applyCoupon(carNo);

        // 2. 결과 메시지 전달
        ra.addFlashAttribute("message", "차량 [" + carNo + "]에 " + discount + "원 할인이 적용되었습니다.");

        // 3. 다시 매장 메인 페이지로 이동
        return "redirect:/store/store";
    }

}
