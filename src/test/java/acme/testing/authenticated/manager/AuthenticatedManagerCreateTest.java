package acme.testing.authenticated.manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedManagerCreateTest extends AcmePlannerTest{
	
	// This test case check that the input boxes are empty, so the authenticated can't become manager and system throws error.
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeBecomeManager(final String username, final String password, final String name, final String surname, final String email) {
		super.signUp(username, password, name, surname, email, "");
		super.signIn(username, password);
		super.clickOnMenu("Account", "Become a Managers");
		super.fillInputBoxIn("company", "");
		super.fillInputBoxIn("sector", "");
		
		super.clickOnSubmitButton("Register");
		super.checkErrorsExist();
		super.signOut();

	}
	
    // This test case check that the input boxes are fill without errors, so the authenticated can become manager
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveBecomeManager(final String username, final String password, final String name, final String surname, final String email, final String company, final String sector) {
		super.signIn(username, password);
		super.clickOnMenu("Account", "Become a Managers");
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.clickOnSubmitButton("Register");
		super.clickOnMenu("Account", "Managers data");		
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("sector", sector);
		super.signOut();

	}

}
