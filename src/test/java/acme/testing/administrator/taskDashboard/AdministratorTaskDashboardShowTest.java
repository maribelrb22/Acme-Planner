package acme.testing.administrator.taskDashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.testing.AcmeWorkPlansTest;

public class AdministratorTaskDashboardShowTest extends AcmeWorkPlansTest{
	
	// Lifecycle management ---------------------------------------------------
	
    // Test cases -------------------------------------------------------------
	
	// This test case verifies that the task indicator panel is displayed correctly
	@Test
	@Order(10)
	public void show() {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Task Dashboard");
		
		super.checkExists(By.tagName("table"));
		super.checkExists(By.tagName("td"));
		
		super.signOut();
	}
	
}
