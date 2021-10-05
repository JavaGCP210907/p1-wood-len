package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.UserDTO;
import com.revature.services.UserService;

import io.javalin.http.Handler;

public class UserController {
	UserService us = new UserService();
	public Handler usernameExistsHandler = (ctx) -> {
		
		String body = ctx.body(); //turn the body (data) of the POST request into a Java String
		
		Gson gson = new Gson();
		
		UserDTO UDTO = gson.fromJson(body, UserDTO.class); //turn that JSON String into a LoginDTO object
		
		ctx.req.getSession(); //req is a "Request Object", we establish sessions through it
			
		boolean exists = us.usernameExists(UDTO.getUsername());
		String rExists = "";
		if(exists) {
			rExists = "1";
		}
		else {
			rExists="0";
		}
		ctx.result(rExists);
		ctx.status(200);
			
	};
	
	public Handler createUserHandler = (ctx) -> {
		
		String body = ctx.body(); //turn the body (data) of the POST request into a Java String
		
		Gson gson = new Gson();
		
		UserDTO UDTO = gson.fromJson(body, UserDTO.class); //turn that JSON String into a LoginDTO object
		
		ctx.req.getSession(); //req is a "Request Object", we establish sessions through it
			
		us.createUser(UDTO.getUsername(), UDTO.getPassword(), UDTO.getFname(), UDTO.getLname(), UDTO.getEmail(), UDTO.getRoleId());
			
	};
}
