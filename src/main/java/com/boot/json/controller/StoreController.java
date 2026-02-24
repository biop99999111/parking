package com.boot.json.controller;

import com.boot.json.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    
      // 매장 페이지    
    @GetMapping
    public String storePage(Model model) {
        int couponCount = storeService.getcouponCount(); 
        model.addAttribute("couponCount", couponCount);
        return "store";
    }
  
      //쿠폰 사용    
    @PostMapping("/coupon/use")
    public String useCoupon(
            @RequestParam String carNo,
            @RequestParam int discountMinutes,
            RedirectAttributes redirectAttributes) {

        int remainCoupon = storeService.applyStoreDiscount(carNo, discountMinutes);

        redirectAttributes.addFlashAttribute("message", "쿠폰이 적용되었습니다!");
        redirectAttributes.addFlashAttribute("couponCount", remainCoupon);

        return "redirect:/store";
    }

    //쿠폰 등록 
    @PostMapping("/coupon/add")
    public String addCoupon(RedirectAttributes redirectAttributes) {
        storeService.addCoupon(); 
        redirectAttributes.addFlashAttribute("message", "쿠폰 1장 등록 완료!");
        return "redirect:/store";
    }
}