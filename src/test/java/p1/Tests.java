package p1;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.LoginService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import com.revature.utils.ConnectionUtil;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {

	private static LoginService ls = new LoginService();
	private static ReimbursementService rs = new ReimbursementService();
	private static UserService us = new UserService();
	
	@BeforeClass
	public static void before() {
		System.out.println("CLEAR");
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "TRUNCATE TABLE reimbursements, users";
			Statement s = conn.createStatement();
			s.executeUpdate(query);
		}
		catch(SQLException e) {
			System.out.println("test clear");
			e.printStackTrace();
		}
	}
	
	@Test
	public void t01createUsers() {
		System.out.println("Test 1: Create User Valid Test");
		us.createUser("username", "password", "John", "Doe", "test@gmail.com", 1);
		us.createUser("user2", "password", "Jane", "Doe", "test2@gmail.com", 1);
		us.createUser("manager", "password", "Jason", "Bourne", "test3@gmail.com", 2);
		System.out.println("   Test Passed");
	}
	
	
	@Test
	public void t02getUserByKeys1() {
		System.out.println("Test 2: Get Users By Keys Valid Test");
		User u = ls.getUserByKeys("username", "password");
		assertEquals(u.getUsername(), "username");
		assertEquals(u.getPassword(), "password");
		assertEquals(u.getFirstName(), "John");
		assertEquals(u.getLastName(), "Doe");
		assertEquals(u.getEmail(), "test@gmail.com");
		assertEquals(u.getRole().getId(), 1);
		u = ls.getUserByKeys("user2", "password");
		assertEquals(u.getUsername(), "user2");
		assertEquals(u.getPassword(), "password");
		assertEquals(u.getFirstName(), "Jane");
		assertEquals(u.getLastName(), "Doe");
		assertEquals(u.getEmail(), "test2@gmail.com");
		assertEquals(u.getRole().getId(), 1);
		u = ls.getUserByKeys("manager", "password");
		assertEquals(u.getUsername(), "manager");
		assertEquals(u.getPassword(), "password");
		assertEquals(u.getFirstName(), "Jason");
		assertEquals(u.getLastName(), "Bourne");
		assertEquals(u.getEmail(), "test3@gmail.com");
		assertEquals(u.getRole().getId(), 2);
		System.out.println("   Test Passed");
	}
	
	@Test
	public void t03getUserByKeys2() {
		System.out.println("Test 3: Get User By Keys Invalid Test");
		User u = ls.getUserByKeys("", "");
		assertNull(u);
		System.out.println("   Test Passed");
	}
	
	@Test
	public void t04getUserByKeys3() {
		System.out.println("Test 4: Get User By Keys Malicious Test");
		User u = ls.getUserByKeys("1 OR 1=1--", "");
		assertNull(u);
		System.out.println("   Test Passed");	
	}
	
	@Test
	public void t05usernameExists1() {
		System.out.println("Test 5: Username does exist");
		boolean exists = us.usernameExists("username");
		assertTrue(exists);
		System.out.println("   Test Passed");
	}
	
	@Test
	public void t06usernameExists2() {
		System.out.println("Test 6: Username does not exist");
		boolean exists = us.usernameExists("user");
		assertFalse(exists);
		System.out.println("   Test Passed");
	}
	
	@Test
	public void t07getRequests1() {
		System.out.println("Test 7: No Requests for a User");
		List<Reimbursement> rl = rs.getRequests(1);
		assert(rl.size() == 0);
		System.out.println("   Test Passed");
	}
	
	@Test
	public void t08getAllRequests1() {
		System.out.println("Test 8: No requests in general");
		List<Reimbursement> rl = rs.getAllRequests();
		assert(rl.size() == 0);
		System.out.println("   Test Passed");
	}
	
	@Test
	public void t09createRequest1() {
		System.out.println("Test 9: Create a Request for John Doe");
		User u = ls.getUserByKeys("username", "password");
		rs.createRequest(u.getId(), "lodging", 100, "test request");
		System.out.println("   Test Passed");
	}
	
	@Test
	public void t10createRequest2() {
		System.out.println("Test 10: Create a Request for Jane Doe");
		User u = ls.getUserByKeys("user2", "password");
		rs.createRequest(u.getId(), "other", 200, "test request 2");
		System.out.println("   Test Passed");
	}
	
	@Test
	public void t11getRequests2() {
		System.out.println("Test 11: Check Requests for a User");
		User u = ls.getUserByKeys("username", "password");
		List<Reimbursement> rl = rs.getRequests(u.getId());
		assertEquals(rl.get(0).getType().getTypeName(), "lodging");
		assertEquals(rl.get(0).getAmount(), 100);
		assertEquals(rl.get(0).getDescription(), "test request");
		assertEquals(rl.get(0).getStatus().getStatusName(), "pending");
		System.out.println("   Test Passed");
	}
	
	@Test
	public void t12getAllRequests2() {
		System.out.println("Test 12: Get all requests");
		List<Reimbursement> rl = rs.getAllRequests();
		assert(rl.size() == 2);
		System.out.println("   Test Passed");
	}
	
	@Test
	public void t13changeStatus1() {
		System.out.println("Test 13: change status of request");
		List<Reimbursement> rl = rs.getAllRequests();
		int id = rl.get(0).getId();
		User u = ls.getUserByKeys("manager", "password");
		rs.changeStatus(id, u.getId(), "approved");
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "select status_id_fk from reimbursements where reimbursment_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			assert(rs.next());
			assertEquals(rs.getInt("status_id_fk"), 2);
		}
		catch(SQLException e) {
			System.out.println("getRequests");
			e.printStackTrace();
		}
		System.out.println("   Test Passed");
	}

	
}
