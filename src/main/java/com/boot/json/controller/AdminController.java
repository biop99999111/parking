package com.boot.json.controller;

import com.boot.json.model.Car;
import com.boot.json.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // 관리자 차량 목록 조회
    @GetMapping("/admin/cars")
    public String getCarList(@RequestParam(defaultValue = "") String keyword, Model model) {
        // 차량 목록 조회
        List<Car> carList = adminService.searchCars(keyword);  // 검색어가 없으면 전체 차량 리스트

        // 모델에 데이터 추가
        model.addAttribute("carList", carList);
        model.addAttribute("keyword", keyword);  // 검색 키워드 전달

        return "admin/carList";  // 차량 목록 페이지로 이동
    }
}
