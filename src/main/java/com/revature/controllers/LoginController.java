package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.LoginService;
import com.revature.utils.JwtUtil;

import io.javalin.http.Handler;

public class LoginController {
	LoginService ls = new LoginService(); 
	
	public Handler getUserByKeysHandler = (ctx) -> {
		
		String body = ctx.body(); //turn the body (data) of the POST request into a Java String
		
		Gson gson = new Gson();
		
		LoginDTO LDTO = gson.fromJson(body, LoginDTO.class); //turn that JSON String into a LoginDTO object
		
		//control flow to determine what happens in the event of a successful/unsuccessful login
		
		//invoke the login() method of LoginService using the username and password in the newly created LoginDTO
		User u = ls.getUserByKeys(LDTO.getUsername(), LDTO.getPassword());
		if( u != null) { //if login is successful...
			
			//generate a JSON Web Token to uniquely identify the user
			String jwt = JwtUtil.generate(LDTO.getUsername(), LDTO.getPassword());
			
			//create a user session
			ctx.req.getSession(); //req is a "Request Object", we establish sessions through it
			
			//successful status code 
			ctx.status(200);
			
			String result = "{\"jwt\": \""+ jwt +"\", \"userId\":" +  u.getId() + ", \"roleId\":" + u.getRole().getId() + "}";
			//ctx.result(result);
			ctx.json(result);
			
		} else { //if login fails...
			
			ctx.status(401); //"unauthorized" status code
			ctx.result("Login Failed! :(");
			
		}
	};
			

}
