package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanDeleteTest extends AcmePlannerTest {

	// This test case check that a manager is able to delete a workplan created by
	// him
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deletePositiveWorkplan(final int id, final String title, final String begin, final String end,
			final String workload, final String executionPeriod) {

		super.signIn("Managers1", "Managers1");
		super.navigate("managers/work-plan/show", String.format("id=%d", id));

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("executionPeriod", executionPeriod);
		super.checkInputBoxHasValue("begin", begin);
		super.checkInputBoxHasValue("end", end);

		super.clickOnSubmitButton("Delete");

		super.navigate("managers/work-plan/show", String.format("id=%d", id));

		super.checkErrorsExist();
		super.navigateHome();

		super.signOut();
		/*
		super.signIn("Managers1", "Managers1");
		super.clickOnMenu("Managers","Workplan list");
		super.clickOnListingRecord(0);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("executionPeriod", executionPeriod);
		super.checkInputBoxHasValue("begin", begin);
		super.checkInputBoxHasValue("end", end);
		super.clickOnSubmitButton("Delete");
		Assertions.assertFalse(title.equals(this.getListingRecord(0).get(1).getText()));

		super.signOut();*/
	}

	// This test case check that a manager can't delete a workplan that he didn't create
	// The id's that are being testing belongs to manager2
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deleteNegativeWorkplan(final int id) {
		super.signIn("Managers1", "Managers1");
		super.navigate("managers/work-plan/delete", String.format("id=%d", id));

		super.checkErrorsExist();

		super.signOut();
	}

}
