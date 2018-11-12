package com.javaspringprojects.securityrestful.dao;

import com.javaspringprojects.securityrestful.entity.Role;

public interface RoleDao {
	
	public Role findRoleByName(String theRoleName);

}
