package com.boot.json.service;

public class StoreService {

    private final StoreMapper storeMapper;
    private final CarMapper carMapper;

    @Transactional // 할인권 차감과 차량 할인 등록은 동시에 성공해야 함
    public void applyStoreDiscount(Long storeId, String carNo, int discountMinutes) {
        // 1. 매장의 할인권 잔여량 확인
        int currentCoupons = storeMapper.getCouponCount(storeId);
        if (currentCoupons <= 0) {
            throw new RuntimeException("잔여 할인권이 없습니다.");
        }

        // 2. 차량에 할인 시간 부여
        carMapper.updateDiscount(carNo, discountMinutes);

        // 3. 매장 할인권 개수 차감
        storeMapper.decreaseCoupon(storeId);
    }
}
