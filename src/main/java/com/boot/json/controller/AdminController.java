package com.boot.json.controller;

import com.boot.json.model.AdminMapper;
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
        return "login"; 
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam("adminId") String adminId, 
                        @RequestParam("password") String password, 
                        @RequestParam("role") String role, 
                        Model model) {

        if (adminService.adminLoginOk(adminId, password)) {
            // [중요] 성공 시 뷰 이름이 아니라 URL 주소로 리다이렉트 해야 합니다.
            if ("ADMIN".equals(role)) {
                return "redirect:/admin";  
            } else if ("STORE".equals(role)) {
                return "redirect:/store";
            }
        }
        
        model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
        return "login";
    }

    // 관리자 페이지 메인 (처음 접속 시)
    @GetMapping("/admin")
    public String adminPage(Model model) {
        // 처음 들어왔을 때 빈 검색어로 전체 리스트를 가져오도록 처리
        List<Car> carList = adminService.searchCar(null, null);
        model.addAttribute("carList", carList);
        return "admin"; 
    }


    @PostMapping("/admin/search")
    public String searchCar(@RequestParam(name = "field", required = false) String field, 
                            @RequestParam(name = "keyword", required = false) String keyword, 
                            Model model) {
        
        // Mapper 호출 (검색 수행)
        List<Car> searchList = adminService.searchCar(field, keyword);

        model.addAttribute("searchList", searchList);
        model.addAttribute("field", field);
        model.addAttribute("keyword", keyword);
        model.addAttribute("count", searchList.size());

        return "adminSearch"; // adminSearch.html로 이동
    }
    
 // 차량 정보 삭제 처리
    @PostMapping("/admin/delete")
    public String deleteCar(@RequestParam(name = "carNo") String carNo) {
        // 서비스의 삭제 메서드 호출 (Mapper의 delete 호출)
        adminService.deleteCar(carNo);
        
        // 삭제 후 전체 리스트 페이지로 리다이렉트
        return "redirect:/admin";
    }



}