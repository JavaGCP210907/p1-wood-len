package com.revature.services;

import com.revature.dao.UserDao;
import com.revature.models.User;

public class LoginService {

	private UserDao uDao = new UserDao();
	
	public User getUserByKeys(String username, String password) {
		return uDao.getUserByKeys(username, password);
	}
	
}
