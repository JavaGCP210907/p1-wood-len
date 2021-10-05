package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Status;
import com.revature.utils.ConnectionUtil;

public class StatusDao implements StatusDaoI {

	@Override
	public Status getStatusById(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "Select * From statuses Where status_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Status(id, rs.getString("status_name"));
			}
		}
		catch(SQLException e) {
			System.out.println("getStatusById");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int getStatusIdByName(String name) {
		try(Connection conn = ConnectionUtil.getConnection()){
		String query = "select * from statuses where status_name = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt("status_id");
			}
		}
		catch(SQLException e) {
			System.out.println("getStatusIdbyName");
			e.printStackTrace();
		}
		return -1;
	}

}
