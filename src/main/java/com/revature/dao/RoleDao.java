package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

public class RoleDao implements RoleDaoI {

	@Override
	public Role getRoleById(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "Select * From roles Where role_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Role(id, rs.getString("role_name"));
			}
		}
		catch(SQLException e) {
			System.out.println("getRoleById");
			e.printStackTrace();
		}
		return null;
	}
}
