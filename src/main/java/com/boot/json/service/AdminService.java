package com.boot.json.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.json.model.AdminMapper;
import com.boot.json.model.Admin1;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;  // AdminMapper 사용하여 DB에서 관리자 정보 조회

    // 관리자 로그인 확인.
    public boolean adminLoginOk(String adminId, String password) {
        // adminId로 관리자 정보 조회 (password는 나중에 설정할 예정)
        Admin1 admin = adminMapper.login(new Admin1(adminId)); // adminId로만 조회
        
        // 관리자 정보가 존재하면 비밀번호 확인
        if (admin != null) {
            // 비밀번호 설정 (DB에서 조회된 관리자 정보의 비밀번호를 설정)
            // 관리자 정보에서 조회된 비밀번호를 비교
            return password.equals(admin.getPassword());  // 비밀번호 일치 여부 확인
        }

        // 관리자정보가 없거나 비밀번호가 일치하지 않으면 false 반환
        return false;
    }
}