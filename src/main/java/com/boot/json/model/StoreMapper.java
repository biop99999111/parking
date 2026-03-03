package com.boot.json.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StoreMapper {
    // 1. 매장 쿠폰 수량 조회
    int getCouponCount(@Param("storeName") String storeName);
    
    // 2. 할인 금액 조회
    int getCouponAmount(@Param("storeName") String storeName);
    
    // 3. 매장 쿠폰 1장 감소
    int decreaseCoupon(@Param("storeName") String storeName);
    
    // 4. 차량에 쿠폰 적용
    int applyCouponToCar(@Param("carNo") String carNo, @Param("couponAmount") int couponAmount);

    // 5. 차량 단건 검색 (Admin용 searchCar와 구분하거나 삭제)

}
