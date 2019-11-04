package com.arijit.services;

import com.arijit.model.Login;
import com.arijit.model.UserInfo;

public interface UserService {
	void register(UserInfo user);

	UserInfo validateUser(Login login);
	boolean updateAccountDetails(UserInfo user);
	
}
