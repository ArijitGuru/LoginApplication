package com.arijit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.arijit.model.UserInfo;
import com.arijit.services.UserService;

@Controller
public class RegistrationController {

	private static final Logger logger = Logger.getLogger(RegistrationController.class);
	
	@Autowired
	public UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("user", new UserInfo());
		logger.debug("showRegister() is executed!");
		return mav;

	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") UserInfo user, HttpSession session) {
		
		String captchaFromUserText = (String)session.getAttribute("captcha_security");
		System.out.println(user.getCaptcha());
		System.out.println(captchaFromUserText);
		
		if (user.getCaptcha().equals(captchaFromUserText)) {
			userService.register(user);
			logger.debug("addUser() is executed!");
			return new ModelAndView("welcome", "user", user);
		}else {
			ModelAndView mav = new ModelAndView("register");
			mav.addObject("user", new UserInfo());
			mav.addObject("message","Captcha information is wrong");
			logger.debug("showRegister() is executed!");
			return mav;
		}
		
	}
}
