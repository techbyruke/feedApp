package com.bptn.feedApp.service;
import com.bptn.feedApp.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.bptn.feedApp.jpa.User;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	
	public List<User> listUsers() {
		return this.userRepository.findAll();
			
	}
	
	public Optional<User> findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}
	
	public void createUser(User user) {
		this.userRepository.save(user);
	}
	
	
}




