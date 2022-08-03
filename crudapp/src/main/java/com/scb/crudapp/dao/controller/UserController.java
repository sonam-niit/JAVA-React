package com.scb.crudapp.dao.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scb.crudapp.customexception.UserNotFoundException;
import com.scb.crudapp.entity.User;
import com.scb.crudapp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService service;
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@PostMapping("")
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		if (service.saveUser(user) > 0) {
			log.info(user +" Registered");
			return new ResponseEntity<Object>("User Created", HttpStatus.CREATED);
		} else
			return new ResponseEntity<Object>("Error while creating User", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping
	public List<User> getAll() {
		return service.getAllUsers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable int id)
	throws UserNotFoundException{
		User user = service.getUserById(id);
		if (user != null)
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		else
			throw new UserNotFoundException("User not available");
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> DeleteUserById(@PathVariable int id)
	throws UserNotFoundException{
		if (service.deleteUser(id)>0)
			return new ResponseEntity<Object>("user Deleted", HttpStatus.OK);
		else
			throw new UserNotFoundException("User not available");
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser
	(@RequestBody User user, @PathVariable int id) 
	throws UserNotFoundException{
		if (service.updateUser(user, id) > 0)
			return new ResponseEntity<Object>("user updated", HttpStatus.OK);
		else
			throw new UserNotFoundException("User not available");

	}

}
