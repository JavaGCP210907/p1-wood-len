package p1;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.LoginService;
import com.revature.services.ReimbursementService;

public class Tests {

	private LoginService ls = new LoginService();
	private ReimbursementService rs = new ReimbursementService();
	
	@Test
	public void getUserByKeys1() {
		System.out.println("Get User By Keys Valid Test");
		User u = ls.getUserByKeys("username", "password");
		assertNotNull(u);
		System.out.println("   Test Passed");
	}
	
	@Test
	public void getUserByKeys2() {
		System.out.println("Get User By Keys Invalid Test");
		User u = ls.getUserByKeys("", "");
		assertNull(u);
		System.out.println("   Test Passed");
	}
	
	@Test
	public void getUserByKeys3() {
		System.out.println("Get User By Keys Malicious Test");
		User u = ls.getUserByKeys("1 OR 1=1--", "");
		assertNull(u);
		System.out.println("   Test Passed");	
	}
	
	@Test
	public void getRequests1() {
		System.out.println("Get Requests Valid Test");
		List<Reimbursement> rl = rs.getRequests(1);
		assert(rl.size() != 0);
		System.out.println("   Test Passed");
	}
	
	@Test
	public void getRequests2() {
		System.out.println("Get Requests Invalid Test");
		List<Reimbursement> rl = rs.getRequests(0);
		assert(rl.size() == 0);
		System.out.println("   Test Passed");
	}
	
	@Test
	public void getAllRequests() {
		System.out.println("Get All Requests Test");
		List<Reimbursement> rl = rs.getAllRequests();
		assert(rl.size() != 0);
		System.out.println("   Test Passed");
	}

	
}
