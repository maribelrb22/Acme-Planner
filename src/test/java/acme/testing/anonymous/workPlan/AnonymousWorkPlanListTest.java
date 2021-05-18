package acme.testing.anonymous.workPlan;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousWorkPlanListTest extends AcmePlannerTest {
	
	// Test cases -------------------------------------------------------------
	
	// This test case check that if you enter as anonymous in workPlan list, the columns and the type of the different input boxes found are the expected.
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/workPlan/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listWorkPlan(final int recordIndex, final String title, final String begin, final String end, final String workload) {
		
		super.clickOnMenu("Anonymous", "Workplan list");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, begin);
		super.checkColumnHasValue(recordIndex, 2, end);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("begin", begin);
		super.checkInputBoxHasValue("end", end);
		super.checkInputBoxHasValue("workload", workload);
		
	}
}
