
package com.boot.json.model;



import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarMapper {

	void insertEntry(String carNo);

	Car selectCar(String carNo);
	
	void setExitCar(String carNo);

	void setParkCar(Car car);






}