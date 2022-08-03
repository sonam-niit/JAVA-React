package com.scb.crudapp.dao;

import java.util.List;

import com.scb.crudapp.entity.User;

public interface UserDao {

	public int saveUser(User user);
	public List<User> getAllUsers();
	public User getUserById(int id);
	public int updateUser(User user, int id);
	public int deleteUser(int id);

}
