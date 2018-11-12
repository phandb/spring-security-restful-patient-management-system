package com.javaspringprojects.securityrestful.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaspringprojects.securityrestful.entity.User;
import com.javaspringprojects.securityrestful.service.UserService;
import com.javaspringprojects.securityrestful.user.CustomRegisterUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class,  stringTrimmerEditor);
		
	}
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("crmUser", new CustomRegisterUser());
		
		return "registration-form";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
			@Valid @ModelAttribute("crmUser") CustomRegisterUser theCrmUser,
			BindingResult theBindingResult,
			Model theModel) {
		
		String userName = theCrmUser.getUserName();
		logger.info("Processing registration form for: " + userName);
		
		//form validation
		if (theBindingResult.hasErrors()) {
			return "registration-form";
		}
		
		//check databse if user already exists
		User existing = userService.findByUserName(userName);
		if (existing != null) {
			theModel.addAttribute("crmUser", new CustomRegisterUser());
			theModel.addAttribute("registration error", "User name already exists.");
			
			logger.warning("User name already exists.");
			return "registration-form";
			
		}
		
		//create user account
		userService.save(theCrmUser);
		
		logger.info("Successfully created user: " + userName);
		
		return "registration-confirmation";
		
	}

}
