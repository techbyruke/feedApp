package com.bptn.feedApp.controller.jdbc;

import java.sql.Timestamp;

public class UserBean {
	
	private Integer user_id;
	private String user_first_name;
	private String user_last_name;
	private String user_handle;
	private String user_password;
	private String user_phone_number;
	private String user_email;
	private Boolean email_verified;
	private Timestamp date_created;
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_first_name() {
		return user_first_name;
	}
	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}
	public String getUser_last_name() {
		return user_last_name;
	}
	public void setUser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
	}
	public String getUser_handle() {
		return user_handle;
	}
	public void setUser_handle(String user_handle) {
		this.user_handle = user_handle;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_phone_number() {
		return user_phone_number;
	}
	public void setUser_phone_number(String user_phone_number) {
		this.user_phone_number = user_phone_number;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public Boolean getEmail_verified() {
		return email_verified;
	}
	public void setEmail_verified(Boolean email_verified) {
		this.email_verified = email_verified;
	}
	public Timestamp getDate_created() {
		return date_created;
	}
	public void setDate_created(Timestamp date_created) {
		this.date_created = date_created;
	}
	
	@Override
	public String toString() {
		return "UserBean [userId=" + user_id + ", firstName=" + user_first_name + ", lastName=" + user_last_name + ", username="
				+ user_handle + ", password=" + user_password + ", phone=" + user_phone_number + ", emailId=" + user_email + ", emailVerified="
				+ email_verified + ", createdOn=" + date_created + "]";
	}
		
	}

	
	


