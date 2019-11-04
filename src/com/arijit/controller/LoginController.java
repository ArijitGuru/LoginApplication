package com.arijit.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.arijit.model.Login;
import com.arijit.model.UserInfo;
import com.arijit.services.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView showHome(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("home", new Login());
		return mav;

	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "logout", required = false) String logout) {
		ModelAndView mav = new ModelAndView("login");
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("password"));
		mav.addObject("login", new Login());
		if (logout != null) {
			mav.addObject("message", "Logged out from application successfully.");
			request.getSession().invalidate();
		}
		return mav;

	}
	 
	 

	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") Login login, HttpSession session) {

		ModelAndView mav = null;
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("password"));
		
		Principal principal = request.getUserPrincipal();
		if (null != principal){
			System.out.println("Principle name is... :" + principal.getName());
		}else {
			System.out.println("Principle name is null");
		}
		
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	     String name = auth.getName(); //get logged in username
	     System.out.println("User is ..: "+ name);
		
		UserInfo user = userService.validateUser(login);
		if (null != user) {
			mav = new ModelAndView("welcome");
			mav.addObject("user", user);
		} else {
			mav = new ModelAndView("login");
			mav.addObject("message", "Username or Password is wrong!!");
		}

		return mav;

	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView showWelcome(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "logout",	required = false) String logout) {
		ModelAndView mav = new ModelAndView("welcome");
		mav.addObject("welcome");
		
		return mav;

	}
	
	public static void main(String args[]) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = "123";
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
	}

}
