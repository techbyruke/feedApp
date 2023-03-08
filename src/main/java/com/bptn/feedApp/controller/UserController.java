package com.bptn.feedApp.controller;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bptn.feedApp.controller.jdbc.UserBean;
import com.bptn.feedApp.service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import java.sql.Timestamp;
import java.time.Instant;


@RestController
@RequestMapping("/user")
public class UserController {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;
	
	@GetMapping("/test")
	public String testController() {

		System.out.println("Hello");
		logger.debug("The testController() method was invoked!");
		return "The FeedApp application is up and running very well";
	
	}
	
	@GetMapping("/")
	public List<UserBean> listUsers() {
		logger.debug("The listUsers() method was invoked!");
		return this.userService.listUsers();
	}
	
	@GetMapping("/{user_handle}")
	public UserBean findByUsername(@PathVariable String user_handle) {
		logger.debug("The findByUsername() method was invoked!, userName={}", user_handle);
		return this.userService.findByUsername(user_handle);
	}
	
	@GetMapping("/{first}/{last}/{username}/{password}/{phone}/{emailId}")
	public String createUser( @PathVariable String first,@PathVariable String last,@PathVariable String username,@PathVariable String password, 
			@PathVariable String phone, @PathVariable String emailId) {
		UserBean user = new UserBean();
		
		user.setUser_first_name(first);
		user.setUser_last_name(last);
		user.setUser_handle(username);
		user.setUser_password(password);
		user.setUser_phone_number(phone);
		user.setUser_email(emailId);
		user.setEmail_verified(false);
		user.setDate_created(Timestamp.from(Instant.now()));
				
		logger.debug("The createUser() method was invoked!, user={}", user.toString());
				
		this.userService.createUser(user);
				
		return "User Created Successfully";
}
	

}
