package com.scb.crudapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scb.crudapp.dao.UserDao;
import com.scb.crudapp.entity.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;
	
	public int saveUser(User user) {
		return dao.saveUser(user);
	}

	@Override
	public List<User> getAllUsers() {
		return dao.getAllUsers();
	}

	@Override
	public User getUserById(int id) {
		return dao.getUserById(id);
	}

	@Override
	public int updateUser(User user, int id) {
		return dao.updateUser(user, id);
	}

	@Override
	public int deleteUser(int id) {
		return dao.deleteUser(id);
	}

}
