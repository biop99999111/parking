package com.boot.json.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Car {

	private String carNo;           // 차량 번호 (PK)
    private LocalDateTime entryTime; // 입차 시간
    private LocalDateTime exitTime;  // 출차 시간
    private int fee;                // 계산된 요금
    private int coupon_using;		// 쿠폰 사용했는지 안했는지 사용했으면 1 안했으면 안바꿔도됨
    private String storeName;
   
}
