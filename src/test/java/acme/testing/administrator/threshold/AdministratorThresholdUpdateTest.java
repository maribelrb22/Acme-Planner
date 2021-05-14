package acme.testing.administrator.threshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorThresholdUpdateTest extends AcmePlannerTest{

	// Lifecycle management ---------------------------------------------------
	
    // Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/threshold/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void updatePositive() {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("administrator", "Spam words list");
	}
	
	// Ancillary methods ------------------------------------------------------
	
}
