
package com.boot.json.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String exitParking(Car car, Model model, RedirectAttributes redirectAttributes) {

	    // 요금계산 및 차량 정보 가져오기
	    Car car_info = service.calculateParkingFee(car);
	    
	    // 출차 처리 (DB에서 출차 메서드 호출)
	    this.mapper.setExitCar(car_info.getCarNo());

	    // 리다이렉트 시 메시지와 차량 정보를 전달
	    redirectAttributes.addFlashAttribute("car_info", car_info);  // 차량 정보
	    redirectAttributes.addFlashAttribute("message", "차량 번호 " + car.getCarNo() + "의 출차가 완료되었습니다.");  // 메시지

	    // 출차 완료 후 리다이렉트하여 정산 화면으로 이동 ("/exitPage")
	    return "redirect:/";  // 정산 화면을 보여줄 다른 URL로 리다이렉트
	}
    
}