package com.bptn.feedApp.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	
	
	
}
