
package com.boot.json.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.boot.json.model.Car;
import com.boot.json.model.CarMapper;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
public class CarController {

	@Autowired
	private CarMapper mapper;

    // 입차 요청 처리
    @PostMapping("/enter")
    public String enterParking(@RequestParam("carNo") String carNo) {
        // 입차 시간 자동 설정
    	
        this.mapper.setParkCar(carNo,LocalDateTime.now());  // 현재 시간을 입차 시간으로 설정

        // DB에 저장

        return "차량 번호 " + carNo + "의 입차가 완료되었습니다.";
    }

    // 출차 요청 처리
    @PostMapping("/exit")
    public String exitParking(@RequestParam("carNo") String carNo) {

        this.mapper.setExitCar(carNo,LocalDateTime.now());  // 출차 메서드
        
        this.mapper.selectCar(carNo);

        // DB에 저장

        return "차량 번호 " + carNo + "의 출차가 완료되었습니다.";
    }
    
}