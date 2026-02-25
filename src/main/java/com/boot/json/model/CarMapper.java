
package com.boot.json.model;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CarMapper {
	
	void insertEntry(String carNo);

	List<Car> selectCar();
	
	void setExitCar(Car car_info);

	void setParkCar(Car car);

	void updatefee(Car car);
	
	void applyCoupon(String carNo);

	Car selectOne(String carNo);







}