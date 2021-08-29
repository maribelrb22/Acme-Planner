package acme.testing.administrator.threshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmeWorkPlansTest;

public class AdministratorThresholdUpdateTest extends AcmeWorkPlansTest{

	// Lifecycle management ---------------------------------------------------
	
    // Test cases -------------------------------------------------------------
	
	// This test case checks the correct update of the spam threshold. After updating this value, 
	// it is expected to return to the initial view of the application
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/threshold/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updatePositive(final String thresholdNumber) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam words list");
		super.clickOnReturnButton("Manage threshold");
		
		super.checkExists(By.id("thresholdNumber"));
		
		super.clickOnReturnButton("Update threshold");
		
		super.fillInputBoxIn("thresholdNumber", thresholdNumber);
		
		super.clickOnSubmitButton("Save changes");
		
		super.checkSimplePath("/master/welcome");
		
		super.signOut();
	}
	
	// This test case checks for errors after inserting wrong data as spam threshold, such as negative 
	// and out of range values, displaying the corresponding error messagee
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/threshold/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void updateNegative(final String thresholdNumber) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam words list");
		super.clickOnReturnButton("Manage threshold");
		
		super.checkExists(By.id("thresholdNumber"));
		
		super.clickOnReturnButton("Update threshold");
		
		super.fillInputBoxIn("thresholdNumber", thresholdNumber);
		
		super.clickOnSubmitButton("Save changes");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------
	
}
