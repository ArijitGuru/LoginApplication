package com.arijit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.arijit.services.UserService;

@Controller
public class TutorialsController {

	private static final Logger logger = Logger.getLogger(TutorialsController.class);
	
	@Autowired
	public UserService userService;

	@RequestMapping(value = "/tutorials", method = RequestMethod.GET)
	public ModelAndView showTutorials(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("showTutorials() is executed!");
		return new ModelAndView("tutorials", "message", "This is Tutorials Page");

	}
}
