package com.revature.dao;

import com.revature.models.Status;

public interface StatusDaoI {

	public Status getStatusById(int id);
	public int getStatusIdByName(String name);
}
