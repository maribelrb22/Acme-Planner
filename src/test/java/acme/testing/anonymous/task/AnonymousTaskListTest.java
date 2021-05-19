package acme.testing.anonymous.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskListTest extends AcmePlannerTest{
	
	// Lifecycle management ---------------------------------------------------
	
    // Test cases -------------------------------------------------------------
	
	// This test case checks the correct vision of the unfinished publics tasks.
		@ParameterizedTest
		@CsvFileSource(resources = "/anonymous/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)	
		public void list(final int recordIndex, final String title, final String begin, final String end,
			final String description, final String link, final String isPublic, final String workload,final String executionPeriod) {
			
			super.clickOnMenu("Anonymous", "Tasks list");
			
			super.checkColumnHasValue(recordIndex, 0, title);
			super.checkColumnHasValue(recordIndex, 1, executionPeriod);
			super.checkColumnHasValue(recordIndex, 2, workload);
			
			super.clickOnListingRecord(recordIndex);
			
			super.checkInputBoxHasValue("title", title);
			super.checkInputBoxHasValue("begin", begin);
			super.checkInputBoxHasValue("end", end);
			super.checkInputBoxHasValue("description", description);
			super.checkInputBoxHasValue("link", link);
			super.checkInputBoxHasValue("workload", workload);
			super.checkInputBoxHasValue("executionPeriod", executionPeriod);
			
		}

}
