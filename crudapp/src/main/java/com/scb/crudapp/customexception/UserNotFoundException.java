package com.scb.crudapp.customexception;

public class UserNotFoundException extends Exception{

	public UserNotFoundException(String msg) {
		super(msg);
	}
	@Override
	public String toString() {
		return "UserNotFoundException- Use not available with Given Id";
	}
	
	
}
