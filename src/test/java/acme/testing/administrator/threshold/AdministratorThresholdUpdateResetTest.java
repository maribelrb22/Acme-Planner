package acme.testing.administrator.threshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmeWorkPlansTest;

public class AdministratorThresholdUpdateResetTest extends AcmeWorkPlansTest{

    // Lifecycle management ---------------------------------------------------
	
    // Test cases -------------------------------------------------------------
	
	// This test case checks the correct reset of the spam threshold after changing the initial default value. 
	// It is expected that after resetting the threshold it will have the value 10.00
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/threshold/update-reset.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updateReset(final String thresholdNumber) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam words list");
		super.clickOnReturnButton("Manage threshold");
		
		super.checkExists(By.id("thresholdNumber"));
		
		super.clickOnReturnButton("Update threshold");
		
		super.fillInputBoxIn("thresholdNumber", thresholdNumber);
		
		super.clickOnSubmitButton("Save changes");
		
		super.checkSimplePath("/master/welcome");
		
		super.clickOnMenu("Administrator", "Spam words list");
		super.clickOnReturnButton("Manage threshold");
		
		super.checkInputBoxHasValue("thresholdNumber", thresholdNumber);
		
		super.clickOnReturnButton("Update threshold");
		super.clickOnSubmitButton("Reset");
		super.clickOnMenu("Administrator", "Spam words list");
		super.clickOnReturnButton("Manage threshold");
		
		super.checkInputBoxHasValue("thresholdNumber", "10.00");
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------
	
}
