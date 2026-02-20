package com.boot.json.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Car {

	private String carNo;           // 차량 번호 (PK)
    private LocalDateTime entryTime; // 입차 시간
    private LocalDateTime exitTime;  // 출차 시간
    private int fee;                // 계산된 요금
    private int coupon;		// 할인 쿠폰 그냥 car_info 테이블에서 coupon 주기를 하면 1로 바뀌도록
    private Long storeId;           // 할인을 제공한 매장 ID
   
}
