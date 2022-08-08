package com.scb.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.scb.crud.entity.User;
import com.scb.crud.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService service;

	@GetMapping("")
	public List<User> getAll() {
		return service.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		return service.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public User saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}

	@PutMapping("{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
		if (service.findById(id).isPresent()) {
			User updated = service.updateUser(user);
			return new ResponseEntity<>(updated, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id) {

		service.deleteUser(id);

		return new ResponseEntity<String>("User deleted successfully!.", HttpStatus.OK);

	}
}
