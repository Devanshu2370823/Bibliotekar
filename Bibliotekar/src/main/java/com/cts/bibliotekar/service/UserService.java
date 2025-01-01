package com.cts.bibliotekar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.bibliotekar.dto.UserDTO;
import com.cts.bibliotekar.entity.User;
import com.cts.bibliotekar.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public User saveUser(UserDTO userDTO) {
		User user = new User();
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setRole(userDTO.getRole());
		return userRepository.save(user);
	}
	
	public List<UserDTO> getAllUsers() {
		return userRepository.findAll().stream()
				.map(user->{
					UserDTO dto = new UserDTO();
					dto.setId(user.getId());
					dto.setName(user.getName());
					dto.setEmail(user.getEmail());
					dto.setRole(user.getRole());
					return dto;
				})
				.toList();
	}
	
	public void delelteUserById(Long id) {
		userRepository.deleteById(id);
	}
}
