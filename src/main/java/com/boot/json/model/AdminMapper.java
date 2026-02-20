package com.boot.json.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
	//관리자-로그인
	Admin1 login(Admin1 admin);
	//관리자-조회
	List<Car> list(Page pvo);
	//관리자-검색
	List<Car> search(Page pvo);
	//관리자-삭제
	int delete(int no);
	
}
