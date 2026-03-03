package com.boot.json.model;

import lombok.Data;

@Data
public class Store {

	private Long storeId;           // 매장 고유 번호
    private String storeName;       // 매장명
    private String password;        // 매장 로그인 비번
    private int couponCount;        // 보유 중인 할인권 개수
}


/*
CREATE TABLE Store (
 
store_id NUMBER PRIMARY KEY,
store_name VARCHAR2(100) NOT NULL,
coupon_amount NUMBER DEFAULT 0
);

CREATE TABLE Store (
    store_id NUMBER PRIMARY KEY,
    store_name VARCHAR2(100) NOT NULL,
    coupon_amount NUMBER DEFAULT 0
);
*/