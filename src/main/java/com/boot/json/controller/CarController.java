package com.boot.json.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.json.model.CarMapper;

@Controller
public class CarController {

	@Autowired
	private CarMapper mapper;
	
	int bb = 1;
}
