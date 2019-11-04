package com.arijit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arijit.dao.UserDao;
import com.arijit.model.Login;
import com.arijit.model.UserInfo;

@Component
public class UserServiceImpl implements UserService {
	@Autowired
	public UserDao userDao;

	public void register(UserInfo user) {
		userDao.register(user);
	}

	public UserInfo validateUser(Login login) {
		return userDao.validateUser(login);
	}

	@Override
	public boolean updateAccountDetails(UserInfo user) {
		
		return  userDao.updateUser(user);
	}
}
