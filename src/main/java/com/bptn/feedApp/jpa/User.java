package com.bptn.feedApp.jpa;



import java.io.Serializable;
import java.sql.Timestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name="\"User\"")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="\"user_id\"")
	private Integer user_id;
	
	@Column(name="\"user_first_name\"")
	private String user_first_name;
		
	@Column(name="\"user_last_name\"")
	private String user_last_name;

	private String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String user_password;
		
	private String user_phone_number;

	@Column(name="\"emailId\"")
	private String emailId;
		
	@Column(name="\"email_verified\"")
	private Boolean email_verified;
		
	@Column(name="\"date_created\"")
	private Timestamp date_created;


public User (){
	
}
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


public String getUsername() {
	return username;
}


public void setUsername(String username) {
	this.username = username;
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


public String getEmailId() {
	return emailId;
}


public void setEmailId(String emailId) {
	this.emailId = emailId;
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
	return String.format(
			"User [user_id=%s, user_first_name=%s, user_last_name=%s, username=%s, user_password=%s, user_phone_number=%s, emailId=%s, email_verified=%s, date_created=%s]",
			user_id, user_first_name, user_last_name, username, user_password, user_phone_number, emailId,
			email_verified, date_created);
}
}