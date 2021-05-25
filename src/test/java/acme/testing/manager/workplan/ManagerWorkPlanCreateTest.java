package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanCreateTest extends AcmePlannerTest {

	// This test case chack that the workplan is sucessful created
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveCreateWorkplan(final int recordIndex, final String title, final String begin, final String end,
			final String isPublic, final String workload, final String executionPeriod) {

		super.signIn("Managers1", "Managers1");
		super.clickOnMenu("Managers", "Create WorkPlan");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("begin", begin);
		super.fillInputBoxIn("end", end);

		if (isPublic.equals("true")) {
			super.fillInputBoxIn("isPublic", "true");
		} else {
			super.fillInputBoxIn("isPublic", "false");
		}

		super.clickOnSubmitButton("Create");

		super.clickOnMenu("Managers", "Workplan list");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, isPublic);
		super.checkColumnHasValue(recordIndex, 2, workload);
		super.checkColumnHasValue(recordIndex, 3, executionPeriod);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("executionPeriod", executionPeriod);
		super.checkInputBoxHasValue("begin", begin);
		super.checkInputBoxHasValue("end", end);

		super.signOut();

	}

	// This test case check that the workplan can't be created because of errors.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeCreateWorkplan(final int recordIndex, final String title, final String begin, final String end,
			final String isPublic) {

		super.signIn("Managers1", "Managers1");
		super.clickOnMenu("Managers", "Create WorkPlan");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("begin", begin);
		super.fillInputBoxIn("end", end);

		if ("true".equals(isPublic)) {
			super.fillInputBoxIn("isPublic", "true");
		} else {
			super.fillInputBoxIn("isPublic", "false");
		}

		super.clickOnSubmitButton("Create");
		super.checkErrorsExist();
		
		super.signOut();
	}

}
