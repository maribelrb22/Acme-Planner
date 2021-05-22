
package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutListRecentTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	/*This test case checks the correct listing of all recent shouts checking every column in the table*/
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list-recent.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listRecent(final int recordIndex, final String moment, final String author, final String text, final String info) {
		
		super.clickOnMenu("Anonymous", "Shouts list");
		
		super.clickOnReturnButton("Recently shouts");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		
	}

}
