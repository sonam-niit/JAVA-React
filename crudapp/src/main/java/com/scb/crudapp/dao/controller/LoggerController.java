package com.scb.crudapp.dao.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggerController {

	@RequestMapping("/log")
	public String logData() {
		
		Logger logger= LoggerFactory.getLogger(LoggerController.class);
		
		logger.info("This is my Info message");
		logger.warn("This is my warning Message");
		logger.error("This is my Error Message");
		
		return "Data Logged";
	}
}
