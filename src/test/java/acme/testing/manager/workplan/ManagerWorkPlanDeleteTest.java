package acme.testing.manager.workplan;

import org.junit.jupiter.api.Assertions;
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
        super.clickOnMenu("Managers","Workplan list");
        super.clickOnListingRecord(0);
        super.checkInputBoxHasValue("title", title);
        super.checkInputBoxHasValue("workload", workload);
        super.checkInputBoxHasValue("executionPeriod", executionPeriod);
        super.checkInputBoxHasValue("begin", begin);
        super.checkInputBoxHasValue("end", end);
        super.clickOnSubmitButton("Delete");
        Assertions.assertFalse(title.equals(this.getListingRecord(0).get(1).getText()));
        super.signOut();
	}

}
