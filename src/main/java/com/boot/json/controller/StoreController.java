package com.boot.json.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boot.json.service.StoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/discount")
    public ResponseEntity<String> applyDiscount(
            @RequestParam Long storeId,
            @RequestParam String carNo,
            @RequestParam int discountMinutes) {

        int remainCoupon = storeService.applyStoreDiscount(storeId, carNo, discountMinutes);

        return ResponseEntity.ok("할인 적용 완료 (남은 쿠폰: " + remainCoupon + "개)");
    }
}
