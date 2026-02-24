package com.boot.json.service;

import com.boot.json.model.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreMapper storeMapper;

    // 매장의 잔여 쿠폰 조회
    public int getcouponCount() {
        return storeMapper.getRemainingCoupon();
    }

    // 할인권 적용
    @Transactional
    public int applyStoreDiscount(String carNo, int discountMinutes) {
       
        int updatedRows = storeMapper.decreaseCoupon(null);

        if (updatedRows == 0) {
            throw new RuntimeException("쿠폰이 부족하여 할인권을 적용할 수 없습니다.");
        }

        // 차량 할인 정보 등록
        Map<String, Object> param = Map.of(
                "carNo", carNo,
                "discountMinutes", discountMinutes
        );
        storeMapper.insertDiscount(param);

        // 남은 쿠폰 수 반환 
        return storeMapper.getRemainingCoupon();
    }

    // 쿠폰 추가 (컨트롤러에서 호출하므로 필요)
    public void addCoupon() {
        storeMapper.increaseCoupon(null);
    }
}