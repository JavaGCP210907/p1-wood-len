package com.revature;

import java.sql.SQLException;

import com.revature.controllers.LoginController;
import com.revature.controllers.ReimbursementController;
import com.revature.dao.ReimbursementDao;
import com.revature.utils.ConnectionUtil;

import io.javalin.Javalin;

public class Main {

	public static void main(String[] args) {
		
		ReimbursementController rc = new ReimbursementController(); 
		LoginController lc = new LoginController();
		
		try{
			ConnectionUtil.getConnection();
		}
		catch(SQLException e) {
			System.out.println("Connection Failed");
			e.printStackTrace();
		}
		
//		UserDao uDao = new UserDao();
//		Role role = new Role(1, "employee");
//		User user = new User("username", "password", "fname", "lname", "test@gmail.com", role);
//		uDao.createUser(user);
//		System.out.println(uDao.usernameExists("us"));
//		System.out.println(uDao.getUserByKeys("username", "password"));
//		ReimbursementDao rDao = new ReimbursementDao();
//		Type t = new Type(4, "other");
//		Status s = new Status(1, "pending");
//		Reimbursement r = new Reimbursement(200, "Once upon a time, a few mistakes ago, I was in your sights, you got me alone.", s, t);
//		User u = uDao.getUserByKeys("username", "password");
//		rDao.createRequest(r, u.getId());
//		System.out.println(rDao.getRequests(u.getId()));
//		rDao.changeStatus(5, 1, "denied");
//		System.out.println(rDao.getRequests(u.getId()));
		
				
		Javalin app = Javalin.create(
				config -> {
					config.enableCorsForAllOrigins(); //allows the server to process JS requests from anywhere
				}
				).start(8090);
		
		app.post("/HomePage", rc.getRequestsHandler);
		app.post("/newReimbursement", rc.createRequestHandler);
		app.post("/Login", lc.getUserByKeysHandler);
		app.post("/changeStatus", rc.changeStatusHandler);
		app.get("/allReimbursements", rc.getAllRequestsHandler);
	}

}