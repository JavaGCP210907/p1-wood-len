package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Type;
import com.revature.utils.ConnectionUtil;

public class TypeDao implements TypeDaoI {

	@Override
	public Type getTypeById(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "Select * From types Where type_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Type(id, rs.getString("type_name"));
			}
		}
		catch(SQLException e) {
			System.out.println("getTypeByID");
			e.printStackTrace();
		}
		return null;
	}

}
