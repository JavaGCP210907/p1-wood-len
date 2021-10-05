package com.revature.dao;

import com.revature.models.User;

public interface UserDaoI {

	public void createUser(User user);
	public boolean usernameExists(String username);
	public User getUserByKeys(String username, String password);
	
}
