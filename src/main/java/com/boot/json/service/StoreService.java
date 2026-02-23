package com.boot.json.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.json.model.StoreMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreMapper storeMapper;


    @Transactional // 할인권 차감과 차량 할인 등록은 동시에 성공해야 함
    public int applyStoreDiscount(Long storeId, String carNo, int discountMinutes) {
        // 1. 매장의 할인권 잔여량 확인
        int updateRow = storeMapper.decreaseCoupon(storeId);
        if (updateRow == 0) {
            throw new RuntimeException("쿠폰이 부족하여 할인권을 적용할 수 없습니다.");
        }

        Map<String, Object> param = Map.of(
        		"storeId", storeId,
        		"carNo", carNo,
        		"discountMinutes", discountMinutes
        		);
        storeMapper.insertDiscount(param);
        
        return storeMapper.getCouponCount(storeId);
    }
}
