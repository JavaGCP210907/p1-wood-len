package com.revature.services;


import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.models.Reimbursement;

public class ReimbursementService {

	ReimbursementDao rDao = new ReimbursementDao();
	
	
	//create a method that gets the DAO data and sends it up to the controller
	//(this method will get called by the controller layer)
	public List<Reimbursement> getRequests(int userId) {
		return rDao.getRequests(userId);
	}
	
	public List<Reimbursement> getAllRequests() {
		return rDao.getAllRequests();
	}
	
	public void createRequest(int userId, String type, int amount, String description) {
		rDao.createRequest(userId, type, amount, description);
	}
	
	public void changeStatus(int rId, int userId, String status) {
		rDao.changeStatus(rId, userId, status);
	}
}
