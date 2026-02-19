package com.boot.json.service;

public class AdminService {

    private final AdminMapper adminMapper;

    // 페이징 처리된 주차 내역 조회
    public List<Car> getHistoryList(Criteria cri) {
        return adminMapper.getListWithPaging(cri);
    }

    // 전체 데이터 개수 (PageDTO 생성용)
    public int getTotalCount(Criteria cri) {
        return adminMapper.getTotalCount(cri);
    }

    // 오늘 총 매출 합계 요약
    public int getTodayTotalRevenue() {
        return adminMapper.selectTodayRevenue();
    }
}
