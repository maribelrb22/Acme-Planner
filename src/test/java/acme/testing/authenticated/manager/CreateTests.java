package acme.testing.authenticated.manager;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class CreateTests extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-up/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positiveBecomeManager(final String username, final String password, final String name, final String surname, final String email,final String phone, final String company, final String sector) {
		super.signUp(username, password, name, surname, email, "");
		super.signIn(username, password);
		super.clickOnMenu("Account", "Become a Managers");
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.clickOnSubmitButton("Register");
		super.checkSimplePath("/master/welcome");

	}

}
