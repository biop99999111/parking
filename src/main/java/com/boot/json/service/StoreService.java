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


    // 차량 검색 & 주차 기록 조회
    public List<Map<String, Object>> getParkingRecords(String carNo, Long storeId) {
        return storeMapper.getParkingRecords(carNo, storeId);
    }

    // 할인권 적용
    @Transactional
    public int applyStoreDiscount(Long storeId, String carNo, int discountMinutes) {   
		int updatedRows = storeMapper.decreaseCoupon(storeId);

        if (updatedRows == 0) {
            throw new RuntimeException("쿠폰이 부족하여 할인권을 적용할 수 없습니다.");
        }

        // 차량 할인 정보 등록
        Map<String, Object> param = Map.of(
        		"storeId", storeId,
                "carNo", carNo,
                "discountMinutes", discountMinutes
        );
        storeMapper.insertDiscount(param);

        // 남은 쿠폰 수 반환 
        return storeMapper.getCouponCount(storeId);
    }

}