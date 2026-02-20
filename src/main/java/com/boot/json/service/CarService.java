package com.boot.json.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.json.model.Car;
import com.boot.json.model.CarMapper;

public class CarService {
	
	@Autowired
    private CarMapper carMapper;

    // 입차 로직
    public void registerEntry(String carNo) {
        carMapper.insertEntry(carNo);
    }

    // 출차 전 요금 계산 로직 (가장 중요)
    public Car calculateParkingFee(String carNo) {
    	
        Car car = carMapper.selectCar(carNo);
        
        // 1. 주차 시간 계산 (분 단위)
        long totalMinutes = Duration.between(car.getEntryTime(), LocalDateTime.now()).toMinutes();
        
        // 2. 요금 계산 (예: 10분당 500원, 할인 시간 차감)
        long billableMinutes = Math.max(0, totalMinutes - car.getDiscountMinutes());
        int finalFee = (int) (billableMinutes / 10) * 500;
        
        
        
        car.setFee(finalFee);
        return car;
    }
    
    }

