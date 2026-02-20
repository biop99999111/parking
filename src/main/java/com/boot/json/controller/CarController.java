
package com.boot.json.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.boot.json.model.Car;
import com.boot.json.model.CarMapper;
import com.boot.json.service.CarService;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
public class CarController {

	@Autowired
	private CarMapper mapper;
    
	@Autowired
	private CarService service;
	
	@RequestMapping("/")
	public String main() {
		return "main";
		
	}
	
    // 입차 요청 처리
    @PostMapping("/enter")
    public String enterParking(Car car, Model model) {
    	
        // DB에 저장
        this.mapper.setParkCar(car);  // DB에 차량 정보 저장
        
        // 차량 번호 중복 확인
        Car existingCar = this.mapper.selectCar(car.getCarNo());  // DB에서 해당 차량 번호 조회

        if (existingCar != null) {
            // 이미 입차된 차량일 경우
            model.addAttribute("message", "차량 번호 " + car.getCarNo() + "는 이미 입차된 차량입니다.");
            return "main";  // 메시지와 함께 메인 페이지로 반환
        }
        

        // 모델에 알림 메시지 추가
        model.addAttribute("message", "차량 번호 " + car.getCarNo() + "의 입차가 완료되었습니다.");

        return "main";  // 메인 페이지로 이동 (여기서는 main.html을 반환)
    }
    
    // 출차 요청 처리
    @PostMapping("/exit")
    public String exitParking(Car car , Model model) {


        
    	// 요금계산및 만약 할인권받았으면 매장등등의 모든정보
        Car car_info = service.calculateParkingFee(car);
        
        this.mapper.setExitCar(car_info.getCarNo());  // 출차 메서드

        // 2. 모델에 차량 정보와 메시지 추가
        model.addAttribute("car_info", car_info);  // 차량 정보 (출차 후 계산된 요금 등)
        model.addAttribute("message", "차량 번호 " + car.getCarNo() + "의 출차가 완료되었습니다.");

        // 3. 출차 완료 후 정산 화면으로 이동 (exit.html)
        return "exit";  // "exit.html" 페이지로 이동
    }
    
}