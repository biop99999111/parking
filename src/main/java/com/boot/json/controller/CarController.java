
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
	
	
	@RequestMapping("/")
	public String main() {
		return "main";
		
	}
	
    // 입차 요청 처리
    @PostMapping("/parking/enter")
    public String enterParking(Car car) {
        // 입차 시간 자동 설정
    	
        this.mapper.setParkCar(car);  // 현재 시간을 입차 시간으로 설정

        // DB에 저장

        return "차량 번호 " + car.getCarNo() + "의 입차가 완료되었습니다.";
    }

    // 출차 요청 처리
    @PostMapping("/parking/exit")
    public String exitParking(Car car) {


        
        Car car_info = this.mapper.selectCar(car.getCarNo());
        
        LocalDateTime enter_time = car_info.getEntryTime();
        
        car.getExitTime();
        
        this.mapper.setExitCar(car.getCarNo());  // 출차 메서드

        // DB에 저장

        return "차량 번호 " + car.getCarNo() + "의 출차가 완료되었습니다.";
    }
    


    @PostMapping("/parking/exit")
    public String exitParking(
            @RequestParam String carNo,
            @RequestParam(required = false) Integer coupon,
            Model model) {

        boolean useCoupon = (coupon != null);

        Car car = CarService.calculatefee(carNo, coupon);
        model.addAttribute("car", car);
        
        return "result";
    }
}