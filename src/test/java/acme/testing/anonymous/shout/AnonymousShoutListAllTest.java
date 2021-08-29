
package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class AnonymousShoutListAllTest extends AcmeWorkPlansTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	//This test case checks the correct listing of every shout in the data base checking every column of the table
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAll(final int recordIndex, final String moment, final String author, final String text, final String info) {

		super.clickOnMenu("Anonymous", "Shouts list");

		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		
	}

}
