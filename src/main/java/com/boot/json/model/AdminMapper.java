package com.boot.json.model; // 패키지 경로 확인

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface AdminMapper {
    Admin1 login(Admin1 admin);
    
    // @Param 이름을 XML의 #{field}, #{keyword}와 일치시켜야 합니다.
    List<Car> searchCar(@Param("field") String field, @Param("keyword") String keyword);
    
    void deleteCar(@Param("carNo") String carNo);
}
