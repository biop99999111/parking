
package com.boot.json.model;



import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CarMapper {
	
	void insertEntry(String carNo);

	Car selectCar(String carNo);
	
	void setExitCar(String carNo);

	void setParkCar(Car car);
	
<<<<<<< HEAD
	void updatefee(Car car);
	
	void applyCoupon(String carNo);
=======
	Car selectOne(String carNo);

>>>>>>> branch 'main' of https://github.com/biop99999111/parking.git






}