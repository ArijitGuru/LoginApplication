package com.arijit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.arijit.services.UserService;



@Controller
public class AdminController {
private static final Logger logger = Logger.getLogger(AdminController.class);
	
	@Autowired
	public UserService userService;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView showAdminPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		logger.debug("showAdminPage() is executed!");
		return new ModelAndView("admin", "message", "This is Admins Page");

	}
}
