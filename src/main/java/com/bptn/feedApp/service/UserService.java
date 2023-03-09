package com.bptn.feedApp.service;

import org.springframework.stereotype.Service;

import com.bptn.feedApp.controller.jdbc.UserBean;
import com.bptn.feedApp.controller.jdbc.UserDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	
	public List<UserBean> listUsers() {
		return this.userDao.listUsers();
			
	}
	
	public UserBean findByUser(String username) {
		return this.userDao.findByUsername(username);
	}
	
	public void createUser(UserBean user) {
		this.userDao.createUser(user);
	}
	
	
}




