
package com.bptn.feedApp.controller.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<UserBean> {

	@Override
	public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		

/* Create a UserBean object*/
UserBean user = new UserBean();

/* Populates the UserBean object with data from the resultSet */
		user.setUser_id(rs.getInt("user_id"));
		user.setUser_first_name(rs.getString("user_first_name"));
		user.setUser_last_name(rs.getString("user_last_name"));
		user.setUsername(rs.getString("username"));
		user.setUser_password(rs.getString("user_password"));
		user.setUser_phone_number(rs.getString("user_phone_number"));
		user.setEmailId(rs.getString("emailId"));
		user.setEmail_verified(rs.getBoolean("email_verified"));
		user.setDate_created(rs.getTimestamp("date_created"));

/* Return the populated UserBean object */
return user;
	
	}

}
