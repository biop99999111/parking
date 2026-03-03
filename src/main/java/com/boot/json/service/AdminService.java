package com.boot.json.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.json.model.AdminMapper;
import com.boot.json.model.Car;
import com.boot.json.model.CarMapper;
import com.boot.json.model.Admin1;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;  // AdminMapper 사용하여 DB에서 관리자 정보 조회\
    
    @Autowired
    private CarMapper carMapper;	// 차량조회용 
    
    

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

    
    // 2. 차량 검색 로직 완성
    public List<Car> searchCar(String field, String keyword) {
        // [수정] 아까 작성한 XML의 id가 searchCar였으므로 해당 메서드를 호출합니다.
        // 만약 검색어가 비어있을 때 전체 조회를 하고 싶다면 아래와 같이 처리 가능합니다.
        
        if (field == null || field.isEmpty() || keyword == null || keyword.isEmpty()) {
            // 검색 조건이 없으면 전체 리스트를 반환하거나, 빈 리스트를 반환하도록 설계
            // 전체 조회가 필요하다면 mapper에 findAll 같은 메서드를 추가해 사용하세요.
            return adminMapper.searchCar(null, null); 
        }

        // 실제 MyBatis를 통한 검색 실행
        List<Car> searchList = adminMapper.searchCar(field, keyword);

        return searchList;
    }
    public void deleteCar(String carNo) {
        adminMapper.deleteCar(carNo);

    }
		
		
}