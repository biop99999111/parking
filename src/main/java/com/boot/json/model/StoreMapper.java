package com.boot.json.model;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;



@Mapper
public interface StoreMapper {

	    Store login(Store store);

	    int getCouponCount(Long storeId);

	    int decreaseCoupon(Long storeId);

	    int insertDiscount(Map<String, Object> param);
	}



