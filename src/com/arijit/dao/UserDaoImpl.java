package com.arijit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.arijit.model.Login;
import com.arijit.model.User;


public class UserDaoImpl implements UserDao {
	private static Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	@Autowired
	DataSource datasource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void register(User user) {
		String sql = "insert into user_detail (name, username, email, password) values(?, ?, ?, ?)";
		logger.debug("UserDaoImpl.register() is being executed..");
		jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getEmail(), user.getPassword());
	}

	public User validateUser(Login login) {
		String sql = "select * from user_detail where username='" + login.getUsername() + "' and password='"
				+ login.getPassword() + "'";
		logger.debug("UserDaoImpl.validateUser() is being executed..");
		List<User> users = jdbcTemplate.query(sql, new UserMapper());
		logger.debug("UserDaoImpl.validateUser() is executed..");
		return users.size() > 0 ? users.get(0) : null;
	}

	@Override
	public boolean updateUser(User user) {
		String sql = "update user_detail set name = ?, username = ?, email = ?, password = ? where user_name = ?";
		logger.debug("UserDaoImpl.register() is being executed..");
		int recordCount = jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), user.getUsername());
		logger.debug("Number of records updated..: "+recordCount);
		return (recordCount == 1 ? true : false);
	}
}

class UserMapper implements RowMapper<User> {
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		return user;

	}
}
