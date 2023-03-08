
package com.bptn.feedApp.controller.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<UserBean> listUsers() {
		String sql = "SELECT * FROM \"User\"";

		return this.jdbcTemplate.query(sql, new UserMapper());
	}

	public UserBean findByUsername(String user_handle) {

		String sql = "SELECT * FROM \"User\" WHERE \"user_handle\" = ?";

		List<UserBean> users = this.jdbcTemplate.query(sql, new UserMapper(), user_handle);

		/*
		 * Returns null if users is empty otherwise, returns the first element in the
		 * list
		 */
		return users.isEmpty() ? null : users.get(0);
	}

	public void createUser(UserBean user) {

		String sql = "INSERT INTO \"User\" (\"user_first_name\", \"user_last_name\", \"user_handle\", \"user_phone_number\", \"user_email\", user_password, \"email_verified\", \"date_created\") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		logger.debug("Insert Query: {}", sql);

		/* Executes the Insert Statement */
		this.jdbcTemplate.update(sql, new Object[] { user.getUser_first_name(), user.getUser_last_name(), user.getUser_handle(),
				user.getUser_phone_number(), user.getUser_email(), user.getUser_password(), user.getEmail_verified(), user.getDate_created() });

	}

}
