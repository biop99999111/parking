package com.boot.json.model;

import java.time.LocalDateTime;

public class Car {

	private String carNo;           // 차량 번호 (PK)
    private LocalDateTime entryTime; // 입차 시간
    private LocalDateTime exitTime;  // 출차 시간
    private int fee;                // 계산된 요금
    private int discountMinutes;    // 적용된 할인 시간(분)
    private Long storeId;           // 할인을 제공한 매장 ID
}
