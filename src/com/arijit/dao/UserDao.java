package com.arijit.dao;

import com.arijit.model.Login;
import com.arijit.model.UserInfo;

public interface UserDao {
	void register(UserInfo user);
	UserInfo validateUser(Login login);
	boolean updateUser(UserInfo user);
	UserInfo getUserInfoWithRole (String username);
}
