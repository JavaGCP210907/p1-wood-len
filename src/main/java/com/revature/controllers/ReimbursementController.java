package com.revature.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.services.ReimbursementService;

import io.javalin.http.Handler;

public class ReimbursementController {
	ReimbursementService rs = new ReimbursementService(); 

	public Handler getRequestsHandler = (ctx) -> {
		
		String body = ctx.body(); //turn the body (data) of the POST request into a Java String
		
		Gson gson = new Gson();
		
		ReimbursementDTO RDTO = gson.fromJson(body, ReimbursementDTO.class); //turn that JSON String into a LoginDTO object
		
		if(rs.getRequests(RDTO.getUserId()) != null) { //if login is successful...
			
			//generate a JSON Web Token to uniquely identify the user
			
			//create a user session
			ctx.req.getSession(); //req is a "Request Object", we establish sessions through it
			
			List<Reimbursement> allR = rs.getRequests(RDTO.getUserId());
			
			//instantiate a Gson object to make JSON <-> POJO conversions (POJO - plain old java object)
			
			String JSONR = gson.toJson(allR);
			
			//successful status code 
			ctx.status(200);
			
			ctx.result(JSONR);
			
		} else { //if login fails...
			
			ctx.status(401); //"unauthorized" status code
			ctx.result("Failed to Load Requests");
			
		}
	};
	
	public Handler getAllRequestsHandler = (ctx) -> {
			
		if(ctx.req.getSession(false) != null) { //if a session exists...
			
			//we create an Array with Avenger data (using the service to talk to the dao)
			List<Reimbursement> allRequests = rs.getAllRequests();
			
			//instantiate a Gson object to make JSON <-> POJO conversions (POJO - plain old java object)
			Gson gson = new Gson();
			
			String JSONRequests = gson.toJson(allRequests); //convert our Java object into a JSON String
			
			ctx.result(JSONRequests); //return our Avengers
			
			ctx.status(200); //200 = OK (success)
			
		} else {
				ctx.status(403); //forbidden status code 
		}
	};
	
	public Handler createRequestHandler = (ctx) -> {
		
		String body = ctx.body(); //turn the body (data) of the POST request into a Java String
		
		Gson gson = new Gson();
		
		ReimbursementDTO RDTO = gson.fromJson(body, ReimbursementDTO.class); //turn that JSON String into a LoginDTO object
		
		ctx.req.getSession(); //req is a "Request Object", we establish sessions through it
			
		rs.createRequest(RDTO.getUserId(), RDTO.getType(), RDTO.getAmount(), RDTO.getDescription());
			
	};
	
	public Handler changeStatusHandler = (ctx) -> {
		String body = ctx.body();
		Gson gson = new Gson();
		ReimbursementDTO RDTO = gson.fromJson(body, ReimbursementDTO.class);
		ctx.req.getSession();
		rs.changeStatus(RDTO.getrId(), RDTO.getUserId(), RDTO.getStatus());
	};
}
