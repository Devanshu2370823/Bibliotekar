package com.cts.bibliotekar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bibliotekar.entity.User;
import com.cts.bibliotekar.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.delelteUserById(id);
	}
}
