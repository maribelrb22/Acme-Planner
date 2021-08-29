package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.testing.AcmeWorkPlansTest;

public class ManagerWorkPlanRemoveTaskTest extends AcmeWorkPlansTest{
	
	// Lifecycle management ---------------------------------------------------
	
    // Test cases -------------------------------------------------------------


    // This test case verifies that all the tasks associated with a work plan can be correctly deleted, 
	// in such a way that when deleting them there is no delete button as there is no associated task with said work plan
	@Test
	@Order(10)
	public void delete() {
		super.signIn("Managers1", "Managers1");
		
        super.clickOnMenu("Managers", "Workplan list");
        super.clickOnListingRecord(0);
        super.clickOnSubmitButton("X");
        
        final String label = "X";
		By locator;

		locator = By.xpath(String.format("//button[@type='submit' and normalize-space()='%s']", label));
		
		assert !super.exists(locator);
        
        super.signOut();
	}

}
