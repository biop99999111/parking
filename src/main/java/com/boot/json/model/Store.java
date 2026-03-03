package com.boot.json.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor  // MyBatis용 기본 생성자
@AllArgsConstructor // 모든 필드 생성자
public class Store {
    // storeId 삭제
    private String storeName;       // 매장명 (STORE_NAME)
    private int couponAmount;       // 할인 금액 (COUPON_AMOUNT)
    private int couponCount;        // 보유 중인 할인권 개수 (COUPON_COUNT)
}


//-- 기존 테이블 삭제 (필요시)
//-- DROP TABLE Store;
//
//-- 신규 생성 (store_name을 기본키로 사용하거나, id를 유지하되 자바에서만 안 쓰셔도 됩니다)
//CREATE TABLE Store (
//    store_name VARCHAR2(100) PRIMARY KEY,
//    coupon_amount NUMBER DEFAULT 0,
//    coupon_count NUMBER DEFAULT 100
//);
//
//-- 데이터 삽입
//INSERT INTO Store (store_name, coupon_amount, coupon_count)
//VALUES ('하이미디어', 1000, 100);
//
//COMMIT;

