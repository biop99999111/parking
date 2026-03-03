package com.boot.json.model;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param; // 임포트 추가

@Mapper
public interface AdminMapper {
    Admin1 login(Admin1 admin);
    void deleteCar(@Param("carNo") String carNo);

    // @Param을 붙여야 XML의 #{field}, #{keyword}와 연결됩니다.
    List<Car> searchCar(@Param("field") String field, @Param("keyword") String keyword);
}
