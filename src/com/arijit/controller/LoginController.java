package com.arijit.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
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
		String captchaText = (String)session.getAttribute("captcha_security");
		
		System.out.println(captchaText);
		
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
	public ModelAndView showWelcome(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("login") Login login, @RequestParam(value = "logout",	required = false) String logout, HttpSession session) {
		
		String captchaText = (String)session.getAttribute("captcha_security");
		String captchaFromUserText = (String)session.getAttribute("captchaFromUser");
		Enumeration<String> params = request.getParameterNames();
		System.out.println(captchaText);
		
		ModelAndView mav = new ModelAndView("welcome");
		mav.addObject("welcome");
		
		return mav;

	}
	
	@RequestMapping(value = "/captcha",method = RequestMethod.GET)
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpg");
		int iTotalChars = 6;
		int iHeight = 40;
		int iWidth = 150;
		Font fntStyle1 = new Font("Arial", Font.BOLD, 30);
		Random randChars = new Random();
		String sImageCode = (Long.toString(Math.abs(randChars.nextLong()), 36)).substring(0, iTotalChars);
		BufferedImage biImage = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2dImage = (Graphics2D) biImage.getGraphics();
		int iCircle = 15;
		for (int i = 0; i < iCircle; i++) {
			g2dImage.setColor(new Color(randChars.nextInt(255), randChars.nextInt(255), randChars.nextInt(255)));
		}
		g2dImage.setFont(fntStyle1);
		for (int i = 0; i < iTotalChars; i++) {
			g2dImage.setColor(new Color(randChars.nextInt(255), randChars.nextInt(255), randChars.nextInt(255)));
			if (i % 2 == 0) {
				g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 24);
			} else {
				g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 35);
			}
		}
		OutputStream osImage = response.getOutputStream();
		ImageIO.write(biImage, "jpeg", osImage);
		g2dImage.dispose();
		HttpSession session = request.getSession();
		session.setAttribute("captcha_security", sImageCode);
	}
	
	public static void main(String args[]) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = "123";
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
	}

}
