package com.bptn.feedApp.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bptn.feedApp.exception.domain.EmailExistException;
import com.bptn.feedApp.exception.domain.UserNotFoundException;
import com.bptn.feedApp.exception.domain.UsernameExistException;
import com.bptn.feedApp.jpa.User;
import com.bptn.feedApp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	    
	@Autowired
	EmailService emailService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
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
	
	
	public void verifyEmail() {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = this.userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException(String.format("Username doesn't exist, %s", username)));

		user.setEmailVerified(true);

		this.userRepository.save(user);
}

	
	private void validateUsernameAndEmail(String username, String emailId) {

		this.userRepository.findByUsername(username).ifPresent(u -> {
			throw new UsernameExistException(String.format("Username already exists, %s", u.getUsername()));
		});

		this.userRepository.findByEmailId(emailId).ifPresent(u -> {
			throw new EmailExistException(String.format("Email already exists, %s", u.getEmailId()));
		});

}
	
	
	public User signup(User user) {

		user.setUsername(user.getUsername().toLowerCase());
		user.setEmailId(user.getEmailId().toLowerCase());

		this.validateUsernameAndEmail(user.getUsername(), user.getEmailId());

		user.setEmailVerified(false);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		user.setCreatedOn(Timestamp.from(Instant.now()));

		this.emailService.sendVerificationEmail(user);

		this.userRepository.save(user);
		return user;

}

		
}
