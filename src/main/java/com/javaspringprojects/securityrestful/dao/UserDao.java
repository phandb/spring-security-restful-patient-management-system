package com.javaspringprojects.securityrestful.dao;

import com.javaspringprojects.securityrestful.entity.User;

public interface UserDao {
	
	public User findByUserName(String userName);
	
	void save(User user);

}
