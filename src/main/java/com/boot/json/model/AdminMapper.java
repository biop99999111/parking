package com.boot.json.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
	//관리자-로그인.
	Admin1 login(Admin1 admin);

}
