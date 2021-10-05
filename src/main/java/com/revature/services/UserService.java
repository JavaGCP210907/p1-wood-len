package com.revature.services;

import com.revature.dao.RoleDao;
import com.revature.dao.UserDao;
import com.revature.models.User;

public class UserService {
	UserDao uDao = new UserDao();
	
	public boolean usernameExists(String username) {
		return uDao.usernameExists(username);
	}
	
	public void createUser(String username, String password, String fname, String lname, String email, int roleId) {
		RoleDao rDao = new RoleDao();
		User u = new User(username, password, fname, lname, email, rDao.getRoleById(roleId));
		uDao.createUser(u);
	}
}
