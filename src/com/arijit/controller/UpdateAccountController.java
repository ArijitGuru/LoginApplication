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
public class UpdateAccountController {

	private static final Logger logger = Logger.getLogger(UpdateAccountController.class);
	
	@Autowired
	public UserService userService;

	@RequestMapping(value = "/updateAccount", method = RequestMethod.GET)
	public ModelAndView showUpdateAccount(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("updateAccount");
		mav.addObject("user", new UserInfo());
		logger.debug("showUpdateAccount() is executed!");
		return mav;

	}

	@RequestMapping(value = "/updateAccountProcess", method = RequestMethod.POST)
	public ModelAndView updateUserAccount(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") UserInfo user, HttpSession session) {
		
		String captchaFromUserText = (String)session.getAttribute("captcha_security");
		System.out.println(user.getCaptcha());
		System.out.println(captchaFromUserText);
		
		if (user.getCaptcha().equals(captchaFromUserText)){
			userService.updateAccountDetails(user);
			logger.debug("updateUserAccount() is executed!");
			return new ModelAndView("updateAccount", "message", "Account details are updated successfully");
		}else {
			ModelAndView mav = new ModelAndView("updateAccount");
			mav.addObject("user", new UserInfo());
			mav.addObject("message", "Captcha information is wrong");
			logger.debug("showUpdateAccount() is executed!");
			return mav;
		}
		

	}
}
