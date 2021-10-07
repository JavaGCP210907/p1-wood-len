package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDao implements ReimbursementDaoI {

	private static StatusDao sDao = new StatusDao();
	private static TypeDao tDao = new TypeDao();
	
	Logger log = LogManager.getLogger(ReimbursementDao.class);
	
	@Override
	public void createRequest(int userId, String type, int amount, String description) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "select * from types where type_name = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, type);
			ResultSet rs = ps.executeQuery();
			int type_id = -1;
			if(rs.next()) {
				type_id = rs.getInt("type_id");
			}
			query = "insert into reimbursements (status_id_fk, type_id_fk, user_id_fk, amount, submit_date, description)" +
			 	   "values (?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, 1);
			ps.setInt(2, type_id);
			ps.setInt(3, userId);
			ps.setInt(4, amount);
			ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			ps.setString(6, description);
			ps.executeUpdate();
			log.info("User " + userId + "created a new reimbursment request");
		}
		catch(SQLException e) {
			System.out.println("createRequest");
			e.printStackTrace();
		}

	}

	@Override 
	public List<Reimbursement> getAllRequests(){
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT first_name, last_name, reimbursment_id, status_id_fk, type_id_fk, amount, description, submit_date, resolved_date FROM reimbursements JOIN users ON user_id_fk = user_id;";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(query);
			ArrayList<Reimbursement> rl = new ArrayList<>();
			while(rs.next()) {
				Reimbursement r = new Reimbursement(
						rs.getInt("reimbursment_id"),
						rs.getInt("amount"), 
						rs.getString("description"), 
						sDao.getStatusById(rs.getInt("status_id_fk")),
						tDao.getTypeById(rs.getInt("type_id_fk")),
						rs.getDate("submit_date"),
						rs.getDate("resolved_date"),
						rs.getString("first_name") + " " + rs.getString("last_name")
						);
				rl.add(r);
			}
			return rl;
		}
		catch(SQLException e) {
			System.out.println("getRequests");
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Reimbursement> getRequests(int userId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "Select * from reimbursements where user_id_fk = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1,userId);
			ResultSet rs = ps.executeQuery();
			ArrayList<Reimbursement> rl = new ArrayList<>();
			while(rs.next()) {
				Reimbursement r = new Reimbursement(
						rs.getInt("reimbursment_id"),
						rs.getInt("amount"), 
						rs.getString("description"), 
						sDao.getStatusById(rs.getInt("status_id_fk")),
						tDao.getTypeById(rs.getInt("type_id_fk")),
						rs.getDate("submit_date"),
						rs.getDate("resolved_date")
						);
				rl.add(r);
			}
			return rl;
		}
		catch(SQLException e) {
			System.out.println("getRequests");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void changeStatus(int rId, int userId, String status) {
		try(Connection conn = ConnectionUtil.getConnection()){
			int statusId = sDao.getStatusIdByName(status);
			String query = "Update reimbursements Set resolver_id_fk = ?, resolved_date = ?, status_id_fk = ? Where reimbursment_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			ps.setInt(3, statusId);
			ps.setInt(4, rId);
			ps.executeUpdate();
			log.info("User" + userId + "changed the status of request " + rId + "to " + status);
		}
		catch(SQLException e) {
			System.out.println("changeStatus");
			e.printStackTrace();
		}

	}

}
