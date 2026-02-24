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

    // 로그인 페이지 이동
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html
    }

    // 관리자 차량 목록 조회
    @GetMapping("/admin/cars")
    public String getCarList(@RequestParam(defaultValue = "") String keyword, Model model) {
        List<Car> carList = adminService.searchCars(keyword); // 검색어 없으면 전체

        model.addAttribute("carList", carList);
        model.addAttribute("keyword", keyword);

        return "admin/carList"; // 차량 목록 페이지
    }
}