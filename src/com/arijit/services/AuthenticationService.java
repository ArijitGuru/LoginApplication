package com.arijit.services;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arijit.dao.UserDao;
import com.arijit.model.UserInfo;

@Service
public class AuthenticationService implements UserDetailsService{

	@Autowired
	public UserDao userDao;
	
	 @Autowired
	 private HttpServletRequest request;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = userDao.getUserInfoWithRole(username);
		GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
		UserDetails userDetails = (UserDetails)new User(userInfo.getUsername(), userInfo.getPassword(), Arrays.asList(authority));
		String captcha = request.getParameter("captcha");
		request.getSession().setAttribute("captchaFromUser", captcha);
		return userDetails;
	}

}
