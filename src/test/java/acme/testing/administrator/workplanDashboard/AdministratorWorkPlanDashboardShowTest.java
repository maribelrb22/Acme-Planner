package acme.testing.administrator.workplanDashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorWorkPlanDashboardShowTest extends AcmePlannerTest{
	
	// Lifecycle management ---------------------------------------------------
	
    // Test cases -------------------------------------------------------------
	
	// This test case verifies that the workplan indicator panel is displayed correctly
	@Test
	@Order(10)
	public void showPanel() {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Workplan Dashboard");
		
		super.checkExists(By.tagName("table"));
		super.checkExists(By.tagName("td"));
		
		super.signOut();
	}
	
	// This test case verifies that the workplan canvas panel is displayed correctly
		@Test
		@Order(20)
		public void showCanvas() {
			super.signIn("administrator", "administrator");
			
			super.clickOnMenu("Administrator", "Workplan Dashboard");
			
			super.checkExists(By.tagName("canvas"));
			
			super.signOut();
		}
	
}
