package com.javaspringprojects.securityrestful.service;

import org.springframework.security.core.userdetails.UserDetailsService;


import com.javaspringprojects.securityrestful.entity.User;
import com.javaspringprojects.securityrestful.user.CustomRegisterUser;


public interface UserService extends UserDetailsService {
	
	User findByUserName(String userName);
	
	void save(CustomRegisterUser customRegisterUser);

}
