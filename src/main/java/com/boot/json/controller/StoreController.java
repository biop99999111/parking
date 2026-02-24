package com.boot.json.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.json.service.StoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/store")

public class StoreController {

 @Autowired
 private StoreService storeService;
 
	// 매장 메인
    @PostMapping
    public String storePage() {
    	return "store";
    }
    
    //할인권 적용
    @PostMapping("/discount")
    public String applyDiscount(
            @RequestParam String carNo,
            @RequestParam int discount,
            RedirectAttributes ra) {
    	
    	storeService.applyStoreDiscount(carNo, discount);
          ra.addFlashAttribute("message","할인이적용되었습니다");
        return "redirect:/store";
    }
}
