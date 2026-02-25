package com.boot.json.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.json.model.AdminMapper;
import com.boot.json.model.Car;

@Service
public class AdminService {

	@Autowired
    private AdminMapper adminMapper;

	// 🚗 차량 검색 기능
    public List<Car> searchCars(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return adminMapper.getAllCars();
        } else {
            return adminMapper.searchCarsByKeyword(keyword);
        }
    }

//    // 페이징 처리된 주차 내역 조회
//    public List<Car> getHistoryList(Criteria cri) {
//        return adminMapper.getListWithPaging(cri);
//    }
//
//    // 전체 데이터 개수 (PageDTO 생성용)
//    public int getTotalCount(Criteria cri) {
//        return adminMapper.getTotalCount(cri);
//    }
//
//    // 오늘 총 매출 합계 요약
//    public int getTodayTotalRevenue() {
//        return adminMapper.selectTodayRevenue();
//    }
}
