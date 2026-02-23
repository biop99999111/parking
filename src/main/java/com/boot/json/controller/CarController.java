package com.boot.json.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.json.model.CarMapper;
import com.boot.json.service.CarService;
<<<<<<< HEAD

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
=======
import com.boot.json.model.Car;
>>>>>>> branch 'main' of https://github.com/biop99999111/parking.git

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
	
	@GetMapping("/enter")
	public String enterParking(Car car, Model model, RedirectAttributes redirectAttributes) {

	    // 차량 번호 중복 확인 (DB에서 해당 차량 번호 조회)
	    Car existingCar = this.mapper.selectOne(car.getCarNo());
	    if (existingCar != null) {
	        // 이미 입차된 차량일 경우
	        redirectAttributes.addFlashAttribute("message", "차량 번호 " + car.getCarNo() + "는 이미 입차된 차량입니다.");
	        return "redirect:/";  // 리다이렉트 시 message 전달
	    }

	    // DB에 저장 (중복 없으면 INSERT)
	    this.mapper.setParkCar(car);

	    // 입차 완료 메시지
	    redirectAttributes.addFlashAttribute("message", "차량 번호 " + car.getCarNo() + "의 입차가 완료되었습니다.");

	    return "redirect:/";  // 리다이렉트 시 message 전달
	}
	
	@GetMapping("/exit")
	public String exitParking(Car car, Model model) {
	    // 1. 요금 계산 및 차량 정보 가져오기 (Service에서 fee, coupon 등이 계산되어 채워짐)
	    Car car_info = service.calculateParkingFee(car);

	    // 2. 출차 처리 (DB 업데이트)
	    this.mapper.setExitCar(car_info.getCarNo());

	    // 3. 다시 메인 페이지로 이동하면서 계산된 car_info를 전달
	    // 이 car_info가 Model에 담겨야 화면에서 모달이 뜹니다.
	    model.addAttribute("car_info", car_info);
	    
	    return "main"; // 메인 HTML 파일명
	}
    
<<<<<<< HEAD


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
=======
	
}
>>>>>>> branch 'main' of https://github.com/biop99999111/parking.git
