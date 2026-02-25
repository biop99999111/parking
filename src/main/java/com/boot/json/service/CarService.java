package com.boot.json.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.json.model.Car;
import com.boot.json.model.CarMapper;

@Service
public class CarService {
    
    @Autowired
    private CarMapper carMapper;

    public Car calculateParkingFee(Car car) {
        // 1. DB에서 차량 정보 조회 (입차 시간 포함)
        Car car_info = carMapper.selectOne(car.getCarNo());
        
        if (car_info == null) {
            // 차량 정보가 없는 경우 예외 처리나 기본값 반환
            return car; 
        }

        // 2. 출차 시간 설정 (Controller에서 넘어온 값)
        car_info.setExitTime(car.getExitTime());
        
        // 3. 주차 시간 계산 (입차와 출차 시각의 차이)
        long totalMinutes = Duration.between(car_info.getEntryTime(), car_info.getExitTime()).toMinutes();
        
        // [수정 포인트 1] 테스트 시 입차 후 바로 출차하면 0분이 나옵니다.
        // 0분이면 요금이 0원이 되므로, 테스트를 위해 최소 1분으로 보정합니다.
        if (totalMinutes <= 0) {
            totalMinutes = 1; 
        }
        
        // [수정 포인트 2] 정수 나눗셈( / 10)은 소수점을 버립니다. (예: 9분 / 10 = 0)
        // Math.ceil을 써서 1분만 주차해도 10분 요금이 나오도록 올림 처리합니다.
        int tenMinuteUnits = (int) Math.ceil((double) totalMinutes / 10);
        int totalFee = tenMinuteUnits * 500; // 10분당 500원
        
        // [수정 포인트 3] 최종 요금 계산 (할인 쿠폰 적용)
        // 쿠폰이 요금보다 클 경우 음수가 나오지 않도록 Math.max(0, ...) 처리합니다.
        int finalFee = Math.max(0, totalFee - car_info.getCoupon());
        
        car_info.setFee(finalFee);

        return car_info;
    }
}