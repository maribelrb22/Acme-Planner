package acme.testing.administrator.word;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

//We are testing list and show spam words
public class TestListShowWord extends AcmeWorkPlansTest{

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/positiveList.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveListShow(final int index, final String word) {
		super.signIn("administrator","administrator");
		super.clickOnMenu("Administrator", "Spam words list");
		
		super.checkColumnHasValue(index, 0, word);
		super.clickOnListingRecord(index);
		
		super.checkInputBoxHasValue("word", word);
		super.checkButtonExists("Update");
		super.checkButtonExists("Delete");
		
		super.signOut();
	}
}
