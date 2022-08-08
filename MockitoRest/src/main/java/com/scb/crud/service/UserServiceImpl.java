package com.scb.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scb.crud.entity.User;
import com.scb.crud.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	@Override
	public List<User> findAll() {
		return repo.findAll();
	}

	@Override
	public Optional<User> findById(int id) {
		return repo.findById(id);
	}

	@Override
	public User saveUser(User user) {
		return repo.save(user);
	}

	@Override
	public User updateUser(User user) {
		return repo.save(user);
	}

	@Override
	public void deleteUser(int id) {
		repo.deleteById(id);

	}

}
