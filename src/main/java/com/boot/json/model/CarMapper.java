
package com.boot.json.model;



import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarMapper {

	void insertEntry(String carNo);

	Car selectCar(String carNo);
	
	void setExitCar(Car car_final_info);

	void setParkCar(Car car);






}