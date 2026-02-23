package com.boot.json.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class Car {

	private String carNo;           // 차량 번호 (PK)

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime entryTime; // 입차 시간
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime exitTime;  // 출차 시간
    
    private int fee;                // 계산된 요금
    private int coupon;		// 쿠폰 금액
    private String storeName;
   
}

/*
    CREATE TABLE Car (
    carNo VARCHAR(20) PRIMARY KEY,            -- 차량 번호 (PK)
    entryTime TIMESTAMP NOT NULL,             -- 입차 시간 (NOT NULL)
    exitTime TIMESTAMP DEFAULT NULL,          -- 출차 시간 (NULL 가능)
    fee INT DEFAULT NULL,                     -- 요금 (NULL 가능)
    coupon INT DEFAULT 0,              		  -- 쿠폰 사용 여부 (기본값 0)
    storeName VARCHAR(255) DEFAULT NULL       -- 매장 이름 (NULL 가능)
);
*/
