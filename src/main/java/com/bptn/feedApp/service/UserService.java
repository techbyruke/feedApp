package com.bptn.feedApp.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bptn.feedApp.exception.domain.EmailExistException;
import com.bptn.feedApp.exception.domain.EmailNotVerifiedException;
import com.bptn.feedApp.exception.domain.UserNotFoundException;
import com.bptn.feedApp.exception.domain.UsernameExistException;
import com.bptn.feedApp.jpa.User;
import com.bptn.feedApp.provider.ResourceProvider;
import com.bptn.feedApp.repository.UserRepository;
import com.bptn.feedApp.security.JwtService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.util.StringUtils;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import org.springframework.http.HttpHeaders;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	    
	@Autowired
	EmailService emailService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;

	@Autowired
	ResourceProvider provider;
	
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
	
	public void sendResetPasswordEmail(String emailId) {

		Optional<User> opt = this.userRepository.findByEmailId(emailId);

		if (opt.isPresent()) {
			this.emailService.sendResetPasswordEmail(opt.get());
		} else {
			logger.debug("Email doesn't exist, {}", emailId);
		}
	}
	
	

	public HttpHeaders generateJwtHeader(String username) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(AUTHORIZATION, this.jwtService.generateJwtToken(username,this.provider.getJwtExpiration()));

		return headers;
	}
	
	public void resetPassword(String password) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = this.userRepository.findByUsername(username)
					.orElseThrow(() -> new UserNotFoundException(String.format("Username doesn't exist, %s",username)));

		user.setPassword(this.passwordEncoder.encode(password));

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
	
		
	private static User isEmailVerified(User user) {
		 
		if (user.getEmailVerified().equals(false)) {
	        throw new EmailNotVerifiedException(String.format("Email requires verification, %s", user.getEmailId()));
	    }	
			
	    return user;
	}
	
	private Authentication authenticate(String username, String password) {
		return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
}
	
	private void updateValue(Supplier<String> getter, Consumer<String> setter) {
		
		Optional.ofNullable(getter.get())
		        //.filter(StringUtils::hasText)
			       .map(String::trim)
			       .ifPresent(setter);
	}
	
	private void updatePassword(Supplier<String> getter, Consumer<String> setter) {

	    Optional.ofNullable(getter.get())
	               .filter(StringUtils::hasText)
	               .map(this.passwordEncoder::encode)
				   .ifPresent(setter);
	}
	
private User updateUser(User user, User currentUser) {
	    
		this.updateValue(user::getFirstName, currentUser::setFirstName);
		this.updateValue(user::getLastName, currentUser::setLastName);
		this.updateValue(user::getPhone, currentUser::setPhone);
		this.updateValue(user::getEmailId, currentUser::setEmailId);
		this.updatePassword(user::getPassword, currentUser::setPassword);

		return this.userRepository.save(currentUser);
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
	public User authenticate(User user) {

		/* Spring Security Authentication. */
		this.authenticate(user.getUsername(), user.getPassword());

		/* Get User from the DB. */
		return this.userRepository.findByUsername(user.getUsername()).map(UserService::isEmailVerified).get();
	}
		
	public User getUser() {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		/* Get User from the DB. */
		return this.userRepository.findByUsername(username)
	.orElseThrow(() -> new UserNotFoundException(String.format("Username doesn't exist, %s",username)));
	}
	
	public User updateUser(User user) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		/* Validates the new email if provided */
		this.userRepository.findByEmailId(user.getEmailId())
	                            .filter(u->!u.getUsername().equals(username))
	                            .ifPresent(u -> {throw new EmailExistException(String.format("Email already exists, %s", u.getEmailId()));});
		    
		/* Get and Update User */	
		return this.userRepository.findByUsername(username)
					            .map(currentUser -> this.updateUser(user, currentUser))
					            .orElseThrow(()-> new UserNotFoundException(String.format("Username doesn't exist, %s", username)));
	}
	
}
