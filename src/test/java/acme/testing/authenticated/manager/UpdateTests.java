package acme.testing.authenticated.manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class UpdateTests extends AcmePlannerTest {

	//Pruebo que al cambiar los datos de manager se cambien correctamente
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/positive-update.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveUpdateManager(final String company, final String sector) {
	
		super.signIn("Managers1", "Managers1");
		
		super.clickOnMenu("Account", "Managers data");		
	
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Account", "Managers data");		
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("sector", sector);		
		
		super.signOut();	
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/negative-update.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeUpdateManager(final String company, final String sector) {
	
		super.signIn("Managers1", "Managers1");
		
		super.clickOnMenu("Account", "Managers data");		
	
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		super.clickOnSubmitButton("Update");
		super.checkErrorsExist();
		
		super.signOut();	
	}
}
