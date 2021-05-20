package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskDeleteTest extends AcmePlannerTest{

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	/*This test case checks the correct  deletion of a task*/
	public void deletePositive(final int recordIndex, final String title, final String begin, final String end,
		final String description, final String link, final String isPublic, final String workload, final String executionPeriod) {
		
		super.signIn("Managers1", "Managers1");
		
		super.clickOnMenu("Managers", "Task list");
		
		/*Click on the 0 = 'recordIndex' task*/
		super.clickOnListingRecord(recordIndex);
		
		/*We store the url which shows the task data*/
		final String urlDeletedTask = super.getCurrentUrl();
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("begin", begin);
		super.checkInputBoxHasValue("end", end);
		super.checkInputBoxHasValue("executionPeriod", executionPeriod);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("description", description);
		
		/*We delete the task*/
		super.clickOnSubmitButton("Delete");	 
		
		super.navigate(urlDeletedTask, "");
		
		super.checkErrorsExist();
		
		super.signOut();
	
	}
	
}
