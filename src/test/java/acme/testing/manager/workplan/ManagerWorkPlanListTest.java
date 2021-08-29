package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class ManagerWorkPlanListTest extends AcmeWorkPlansTest {

	// This test case check that if you enter as manager in workPlan list, the
	// columns and the type of the different input boxes found are the expected.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex, final String title, final String begin, final String end,
			final String isPublic, final String workload, final String executionPeriod, final String tasks) {

		super.signIn("Managers1", "Managers1");
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

		if (tasks.length() != 0) {
			String[] parts = tasks.split(";");
			for (int i = 0; i < parts.length; i += 3) {
				super.checkColumnHasValue(i / 3, 0, parts[i].trim());
				super.checkColumnHasValue(i / 3, 1, parts[i + 1].trim());
				super.checkColumnHasValue(i / 3, 2, parts[i + 2].trim());
			}
		}

		super.signOut();

	}
}
