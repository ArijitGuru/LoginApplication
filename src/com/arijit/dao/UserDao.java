package com.arijit.dao;

import com.arijit.model.Login;
import com.arijit.model.User;

public interface UserDao {
	void register(User user);
	User validateUser(Login login);
	boolean updateUser(User user);
}
