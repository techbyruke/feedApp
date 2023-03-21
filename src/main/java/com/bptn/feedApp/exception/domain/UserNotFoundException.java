package com.bptn.feedApp.exception.domain;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message) {
		super(message);
		
	}

	private static final long serialVersionUID = 1L;

}
