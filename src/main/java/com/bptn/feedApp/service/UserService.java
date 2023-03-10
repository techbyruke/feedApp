package com.bptn.feedApp.service;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bptn.feedApp.repository.UserRepository;

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
	
	public void deleteUserById(Integer userId) {
		userRepository.deleteById(userId);
	}

	public void createUser(User user) {
		this.userRepository.save(user);
	}
	
public User signup(User user){
		
		user.setUserName(user.getUserName().toLowerCase());
		user.setEmailId(user.getEmailId().toLowerCase());
		
		user.setEmailVerified(false);
		
		user.setCreatedOn(Timestamp.from(Instant.now()));
		
		this.userRepository.save(user);
		
		return user;
		
	}

}




