package com.boot.json.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Car {

	private String carNo;           // 차량 번호 (PK)
    private LocalDateTime entryTime; // 입차 시간
    private LocalDateTime exitTime;  // 출차 시간
    private int fee;                // 계산된 요금
    private int coupon;		// 쿠폰 금액(예 5000), 안썼으면 0
    private String storeName;
   
}
