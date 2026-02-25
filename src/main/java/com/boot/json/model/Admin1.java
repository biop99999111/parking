package com.boot.json.model;

import lombok.Data;

@Data
public class Admin1 {
	
	private String adminId;         // 관리자 아이디
    private String password;        // 관리자 비밀번호
    private String role;            // 권한 (SUPER, STAFF 등)

    // adminId를 받는 생성자
    public Admin1(String adminId) {
        this.adminId = adminId;
    }

    // 비밀번호 설정 메서드 (password는 setter로 설정)
    public void setPassword(String password) {
        this.password = password;
    }
}