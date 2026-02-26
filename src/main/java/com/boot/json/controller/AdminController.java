package com.boot.json.controller;

import com.boot.json.model.Car;
import com.boot.json.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

	@Autowired
    private AdminService adminService;

    // 로그인 페이지 이동
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html
    }
    
    // 로그인 처리 (POST)
    @PostMapping("/login")
    public String login(@RequestParam String adminId, 
                        @RequestParam String password, 
                        @RequestParam String role, 
                        Model model) {

    	// 로그인 인증
        if (adminService.adminLoginOk(adminId, password)) {
            // 로그인 성공 시 role에 따라 리다이렉트
            if ("ADMIN".equals(role)) {
                return "redirect:/admin";  // 관리자 페이지로 리다이렉트
            } else if ("STORE".equals(role)) {
                return "redirect:/store";  // 매장 페이지로 리다이렉트
            }
        } else {
            // 로그인 실패 시 오류 메시지 전달
            model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "login";  // 로그인 페이지로 다시 리턴
        }
        return "login";  // 기본적으로 로그인 실패 시
    }
    
}
