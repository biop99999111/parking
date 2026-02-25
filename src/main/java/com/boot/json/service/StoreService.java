package com.boot.json.service;

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

    // 매장의 잔여 쿠폰 조회
    public int getcouponCount(Long storeId) {
        return storeMapper.getCouponCount(storeId);
    }


    // 차량 검색 
    public List<Map<String, Object>> searchCar(String carNo) {
    	 return storeMapper.searchCar(carNo);
    }
    

    //할인권 적용
    @Transactional
    public int applyStoreDiscount(Long storeId, String carNo) {   
    	// 매장 전체 쿠폰 수량 
		int affectedRows = storeMapper.updateStoreCouponDecrease(storeId);
        if (affectedRows <= 0) {
            throw new RuntimeException(" 매장에 잔여 쿠폰이 없습니다.");
        }
        
        //차량 쿠폰 적용
        storeMapper.applyCouponToCar(carNo);
        
        //남은 쿠폰 수 반환 
        return storeMapper.getCouponCount(storeId);
    }
}

