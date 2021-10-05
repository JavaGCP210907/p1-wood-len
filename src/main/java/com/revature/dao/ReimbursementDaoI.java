package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDaoI {

	public void createRequest(int userId, String type, int amount, String description);
	public List<Reimbursement> getAllRequests();
	public List<Reimbursement> getRequests(int userId);
	public void changeStatus(int rId, int userId, String status);	
}
