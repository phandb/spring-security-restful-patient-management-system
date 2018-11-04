package com.javaspringprojects.securityrestful.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
	
	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	//add requestin mapping for /leaders
	@GetMapping("/leaders")
	public String showLeaders() {
		return "leaders";
	}
	
	//add requestin mapping for /systems
		@GetMapping("/systems")
		public String showSystems() {
			return "systems";
		}

}
