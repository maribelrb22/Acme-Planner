package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskCreateTest extends AcmePlannerTest {
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	//Esta caso de puebra consiste em distintas inserciones de tareas por un manager correctamente
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String title, final String begin, final String end,
		final String description, final String link, final String isPublic, final String workload) {
		
		super.signIn("Managers1", "Managers1");

		super.clickOnMenu("Managers", "Create Task");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("begin", begin);
		super.fillInputBoxIn("end", end);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		if(isPublic.equals("true")) {
		super.fillInputBoxIn("isPublic", "true");
		}
		else{
		super.fillInputBoxIn("isPublic", "false");
		}
		super.fillInputBoxIn("workload", workload);
		super.clickOnSubmitButton("Create");

		super.clickOnMenu("Managers", "Task list");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 2, workload);
		super.checkColumnHasValue(recordIndex, 3, isPublic);

		super.clickOnListingRecord(recordIndex);
	
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("begin", begin);
		super.checkInputBoxHasValue("end", end);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("isPublic", isPublic);
		super.checkInputBoxHasValue("workload", workload);

		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String title, final String begin, final String end,
		final String description, final String link, final String isPublic, final String workload) {
		
		super.signIn("Managers2", "Managers2");

		super.clickOnMenu("Managers", "Create Task");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("begin", begin);
		super.fillInputBoxIn("end", end);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		if(isPublic.equals("true"))
			super.fillInputBoxIn("isPublic", "true");
		else if(isPublic.equals("false"))
			super.fillInputBoxIn("isPublic", "false");
		super.fillInputBoxIn("workload", workload);
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();

		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------

}
