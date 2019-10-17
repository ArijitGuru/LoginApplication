package com.arijit.services;

import com.arijit.model.Login;
import com.arijit.model.User;

public interface UserService {
	void register(User user);
	User validateUser(Login login);
}
