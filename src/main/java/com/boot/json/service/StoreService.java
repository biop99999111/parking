package com.boot.json.service;

import com.boot.json.model.Car;
import com.boot.json.model.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreMapper storeMapper;
    
    // 차량 검색 메서드 추가
  

    // 쿠폰 금액 조회 메서드 추가
    public int getCouponCount(String storeName) {
        return storeMapper.getCouponCount(storeName);
    }

    @Transactional
    public int applyCoupon(String carNo) {
    
        // 매장명 
        String storeName = "하이미디어";
 
        // 쿠폰 할인 금액 조회
        int couponAmount = storeMapper.getCouponAmount(storeName);

        // 차량에 쿠폰 적용
        storeMapper.applyCouponToCar(carNo, couponAmount);
        storeMapper.decreaseCoupon(storeName);

        return couponAmount;
    }
    
    
}