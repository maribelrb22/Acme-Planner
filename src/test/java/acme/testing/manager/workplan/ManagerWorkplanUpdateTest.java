package acme.testing.manager.workplan;

import acme.testing.AcmePlannerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Date;

public class ManagerWorkplanUpdateTest extends AcmePlannerTest {
    //We are updating an existing workplan with correct data
    @ParameterizedTest
    @CsvFileSource(resources = "/manager/workplan/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
    public void positiveUpdateWorkplan(final String title, final String startDate, final String endDate) {
        super.signIn("Managers1", "Managers1");
        super.clickOnMenu("Managers", "Workplan list");
        super.clickOnListingRecord(0);
        super.fillInputBoxIn("title", title);
        super.fillInputBoxIn("begin", startDate);
        super.fillInputBoxIn("end", endDate);
        super.clickOnSubmitButton("Update");
        Assertions.assertEquals(title, this.getListingRecord(0).get(1).getText());
    }
    // We are updating an existing workplan with incorrect or empty data expecting to fail
    @ParameterizedTest
    @CsvFileSource(resources = "/manager/workplan/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(20)
    public void negativeUpdateWorkplan(final String title, final String startDate, final String endDate) {
        super.signIn("Managers1", "Managers1");
        super.clickOnMenu("Managers", "Workplan list");
        super.clickOnListingRecord(0);
        super.fillInputBoxIn("title", title);
        super.fillInputBoxIn("begin", startDate);
        super.fillInputBoxIn("end", endDate);
        super.clickOnSubmitButton("Update");
        super.checkErrorsExist();
    }

}
