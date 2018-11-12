package com.javaspringprojects.securityrestful.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaspringprojects.securityrestful.dao.RoleDao;
import com.javaspringprojects.securityrestful.dao.UserDao;
import com.javaspringprojects.securityrestful.entity.Role;
import com.javaspringprojects.securityrestful.entity.User;
import com.javaspringprojects.securityrestful.user.CustomRegisterUser;

@Service
public class UserServiceImpl implements UserService {
	
	//need to inject user dao
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}
	
	@Override
	@Transactional
	public void save(CustomRegisterUser customRegisterUser) {
		
		User user = new User();
		// assign user details to the user object
		user.setUserName(customRegisterUser.getUserName());
		user.setPassword(passwordEncoder.encode(customRegisterUser.getPassword()));
		user.setFirstName(customRegisterUser.getFirstName());
		user.setLastName(customRegisterUser.getLastName());
		user.setEmail(customRegisterUser.getEmail());
		
		//then give user default role of "employee"
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));
		
		//save user in the database
		userDao.save(user);

	}


	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	
}
