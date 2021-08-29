package acme.testing.anonymous.workPlan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class AnonymousWorkPlanShowTest extends AcmeWorkPlansTest{
	
	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/workPlan/tasks-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listWorkPlanTasks(final int recordIndex, final String tasks) {
		
		super.clickOnMenu("Anonymous", "Workplan list");
		
		super.clickOnListingRecord(recordIndex);
		
		if (tasks.length() != 0) {
			final String[] parts = tasks.split(";");
			for (int i = 0; i < parts.length; i += 3) {
				super.checkColumnHasValue(i / 3, 0, parts[i].trim());
				super.checkColumnHasValue(i / 3, 1, parts[i + 1].trim());
				super.checkColumnHasValue(i / 3, 2, parts[i + 2].trim());
			}
		}
	}

}
