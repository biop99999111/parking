package com.boot.json.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.json.model.AdminMapper;
import com.boot.json.model.Car;

@Service
public class AdminService {

	@Autowired
    private AdminMapper adminMapper;
}
