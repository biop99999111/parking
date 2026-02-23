package com.boot.json.model;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreMapper {

	    Store login(Store store);

	    int getCouponCount(Long storeId);

	    int decreaseCoupon(Long storeId);

	    int insertDiscount(Map<String, Object> param);
	}



