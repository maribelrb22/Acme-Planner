package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanPrivatizeTest extends AcmePlannerTest {

	// This test case check that the workplan can be privatized
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/privatize-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePrivatizeWorkplan(final int recordIndex, final String title, final String begin,
			final String end, final String isPublic, final String workload, final String executionPeriod) {

		super.signIn("Managers1", "Managers1");
		super.clickOnMenu("Managers", "Workplan list");

		super.checkColumnHasValue(recordIndex, 1, isPublic);
		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("executionPeriod", executionPeriod);
		super.checkInputBoxHasValue("begin", begin);
		super.checkInputBoxHasValue("end", end);

		super.clickOnSubmitButton("Privatize");
		super.clickOnMenu("Managers", "Workplan list");

		super.checkColumnHasValue(recordIndex, 1, "false");

		super.signOut();
	}

	// This test case check that the workplan can't be privatized because of an incorrect manager
	// The id's that are being testing belongs to manager2
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/privatize-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativePrivatizeWorkplan(final int id) {
		super.signIn("Managers1", "Managers1");
		super.navigate("managers/work-plan/privatize", String.format("id=%d", id));

		super.checkErrorsExist();

		super.signOut();
	}
}
