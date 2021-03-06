package com.javaspringprojects.securityrestful.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/showCustomLoginPage")
	public String showCustomLoginPage() {
		
		return "fancy-login";
	}
	
	//add request mapping for /access-denied
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "access-denied";
	}
}
