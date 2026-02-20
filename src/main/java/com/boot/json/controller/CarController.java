package com.boot.json.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.boot.board.controller.GetMapping;
import com.boot.board.controller.Model;
import com.boot.board.controller.RequestParam;
import com.boot.board.model.Board;
import com.boot.board.model.Page;
import com.boot.json.model.CarMapper;

@Controller
public class CarController {

	@Autowired
	private CarMapper mapper;
	
	@GetMapping("parking")
	public String parking() {
		return "car_parking";
	}
	
	
	

}
