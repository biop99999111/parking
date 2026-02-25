package com.boot.json.model;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;




@Mapper
public interface StoreMapper {

	    List<Map<String, Object>> searchCar(@Param("carNo") String carNo);

	     //매장 잔여 쿠폰 조회
	    int getCouponCount(@Param("storeId") Long storeId);
	    
         // 쿠폰 수량 차감
	    int updateStoreCouponDecrease(@Param("storeId") Long storeId);
	    
	     // 차량에 쿠폰 적용
	    int applyCouponToCar(@Param("carNo") String carNo);  
	}




