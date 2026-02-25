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
    private final Long storeId = 1L;
   
    
   
      //매장 페이지    
    @GetMapping
    public String storePage(Model model) {
    	
        int couponCount = storeService.getcouponCount(storeId); 
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
    
      //쿠폰 사용    
    @PostMapping("/coupon/use")
    public String useCoupon(
            @RequestParam String carNo,
            @RequestParam int discountMinutes,
            RedirectAttributes redirectAttributes) {

        try {
            int remainCoupon = storeService.applyStoreDiscount(storeId, carNo);
            redirectAttributes.addFlashAttribute("message", "쿠폰이 적용되었습니다!");
            redirectAttributes.addFlashAttribute("couponCount", remainCoupon);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/store";
    }
}
