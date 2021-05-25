package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskUpdateTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	/*This test case checks the correct task update beeing a manager. The execution period is calculated automatically
	 * so that's why we don't update it. When the start o end date is updated this field is automatically updated */
	public void updatePositive(final int recordIndex, final String title, final String begin, final String end,
		final String description, final String link, final String isPublic, final String workload, final String executionPeriod) {
		
		super.signIn("Managers2", "Managers2");
		
		super.clickOnMenu("Managers", "Task list");
				
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("begin", begin);
		super.fillInputBoxIn("end", end);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("workload", workload);


		super.clickOnSubmitButton("Update");
		super.checkSimplePath("/managers/task/list");
		super.clickOnMenu("Managers", "Task list");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, executionPeriod); /*No comprobamos este campo pues si hemos cambiado la fecha de inicio o fin este campo varía automáticamente*/
		super.checkColumnHasValue(recordIndex, 2, workload);
		super.checkColumnHasValue(recordIndex, 3, isPublic);
		
		super.clickOnListingRecord(recordIndex);
		
		/*Comprobamos que los campos se han modificado correctamente*/
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("begin", begin);
		super.checkInputBoxHasValue("end", end);
		super.checkInputBoxHasValue("executionPeriod", executionPeriod);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("description", description);
		
		super.clickOnReturnButton("Return");
		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	/*This test case checks the correct denial of update a task when some field is incorrect, empty or is cosidered a word spam */
	public void updateNegative(final int recordIndex, final String title, final String begin, final String end,
		final String description, final String workload) {
		
		super.signIn("Managers2", "Managers2");
		
		super.clickOnMenu("Managers", "Task list");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("begin", begin);
		super.fillInputBoxIn("end", end);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();

		
	}

}
