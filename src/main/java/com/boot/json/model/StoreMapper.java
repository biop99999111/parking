package com.boot.json.model;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface StoreMapper {

	     // 차량 검색
	    Car searchCar(@Param("carNo") String carNo);

	     // 매장 잔여 쿠폰 조회
	    int getCouponCount(@Param("storeName") String storeName);
	    
         // 쿠폰 할인 금액 
	    int getCouponAmount(@Param("storeName") String storeName);
	    
	    // 매장쿠폰 1장 감소
	    int decreaseCoupon(@Param("storeName") String storeName);
	    
	     // 차량에 쿠폰 적용
	    int applyCouponToCar(@Param("carNo") String carNo,
                @Param("couponAmount") int couponAmount);
}




