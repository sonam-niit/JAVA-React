package com.scb.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scb.crud.entity.User;
import com.scb.crud.repo.UserRepository;

public interface UserService {
	
	public List<User> findAll();
	public Optional<User> findById(int id);
	public User saveUser(User user);
	public User updateUser(User user);
	public void deleteUser(int id);
}
