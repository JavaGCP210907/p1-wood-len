package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = System.getenv("url");
		String username = System.getenv("username");
		String password = System.getenv("password");
		
		return DriverManager.getConnection(url, username, password);
	}
}