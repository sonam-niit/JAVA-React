package com.scb.crudapp.dao.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.scb.crudapp.customexception.UserNotFoundException;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<Object> handler(UserNotFoundException exception){
		return new ResponseEntity<Object>(exception.getMessage(),
				HttpStatus.NOT_FOUND);
	}
}
