package com.arijit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.arijit.model.Login;
import com.arijit.model.UserInfo;


public class UserDaoImpl implements UserDao {
	private static Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	@Autowired
	DataSource datasource;

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	PasswordEncoder encoder = new BCryptPasswordEncoder();

	public void register(UserInfo user) {
		String sql = "insert into user_detail (name, username, email, password) values(?, ?, ?, ?)";
		logger.debug("UserDaoImpl.register() is being executed..");
		jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getEmail(), user.getPassword());
	}

	public UserInfo validateUser(Login login) {
		String encodedPassword = encoder.encode(login.getPassword());
		String sql = "select * from user_detail where username='" + login.getUsername() + "' and password='"
				+ login.getPassword() + "'";
		logger.debug("UserDaoImpl.validateUser() is being executed..");
		List<UserInfo> users = jdbcTemplate.query(sql, new UserMapper());
		logger.debug("UserDaoImpl.validateUser() is executed..");
		return users.size() > 0 ? users.get(0) : null;
	}

	@Override
	public boolean updateUser(UserInfo user) {
		String sql = "update user_detail set name = ?, username = ?, email = ?, password = ? where user_name = ?";
		logger.debug("UserDaoImpl.register() is being executed..");
		int recordCount = jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), user.getUsername());
		logger.debug("Number of records updated..: "+recordCount);
		return (recordCount == 1 ? true : false);
	}
	
	public UserInfo getUserInfoWithRole(String username){
    	String sql = "SELECT u.username name, u.password pass, a.role role FROM "+
    			     "user_detail u INNER JOIN authorities a on u.username=a.username WHERE "+
    			     "u.enabled =1 and u.username = ?";
    	UserInfo userInfo = (UserInfo)jdbcTemplate.queryForObject(sql,  new Object[]{username},  new UserWithRoleMapper());
    	return userInfo;
	}	
}

class UserMapper implements RowMapper<UserInfo> {
	public UserInfo mapRow(ResultSet rs, int arg1) throws SQLException {
		UserInfo user = new UserInfo();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		return user;

	}
}

class UserWithRoleMapper implements RowMapper<UserInfo> {
	public UserInfo mapRow(ResultSet rs, int arg1) throws SQLException {
		UserInfo user = new UserInfo();
		user.setUsername(rs.getString("name"));
		user.setPassword(rs.getString("pass"));
		user.setRole(rs.getString("role"));
		return user;

	}
}
