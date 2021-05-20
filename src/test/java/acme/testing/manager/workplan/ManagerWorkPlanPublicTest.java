package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanPublicTest extends AcmePlannerTest {

	// This test case check that the workplan can be published
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/public-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePublishWorkplan(final int recordIndex, final String title, final String begin,
			final String end, final String isPublic, final String workload, final String executionPeriod) {

		super.signIn("Managers1", "Managers1");
		super.clickOnMenu("Managers", "Workplan list");

		super.checkColumnHasValue(recordIndex, 1, isPublic); //check that it's private
		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("executionPeriod", executionPeriod);
		super.checkInputBoxHasValue("begin", begin);
		super.checkInputBoxHasValue("end", end);

		super.clickOnSubmitButton("Publish");
		super.clickOnMenu("Managers", "Workplan list");

		super.checkColumnHasValue(recordIndex, 1, "true");

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/privatize-public-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativePublishWorkplan(final int id) {
		super.signIn("Managers1", "Managers1");
		super.navigate("managers/work-plan/publish", String.format("id=%d", id));

		super.checkErrorsExist();

		super.signOut();
	}
}
