package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDao implements UserDaoI {

	private RoleDao roleDao = new RoleDao();
	@Override
	public void createUser(User user) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "insert into users (role_id_fk, username, password, first_name, last_name, email)" +
				 	   "values (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1,user.getRole().getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getFirstName());
			ps.setString(5, user.getLastName());
			ps.setString(6, user.getEmail());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("createUser");
			e.printStackTrace();
		}
		
	}
	
	@Override 
	public boolean usernameExists(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "Select * From users Where username = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}
		catch(SQLException e) {
			System.out.println("usernameExists");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User getUserByKeys(String username, String password) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "Select * From users Where username = ? and password = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new User(
						rs.getInt("user_id"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("email"),
						roleDao.getRoleById(rs.getInt("role_id_fk"))
						);
			}
		}
		catch(SQLException e) {
			System.out.println("getUserByKeys");
			e.printStackTrace();
		}
		return null;
	}

}
