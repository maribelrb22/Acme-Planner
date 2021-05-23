package acme.testing.administrator.word;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

//We are testing positive and negative cases for editing spam words
public class TestUpdateWord extends AcmePlannerTest{
    @ParameterizedTest
    @CsvFileSource(resources = "/administrator.word/positiveUpdateWord.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
    public void PositiveUpdateWord(final String word, final Integer index) {
        super.signIn("administrator", "administrator");
        super.clickOnMenu("Administrator", "Spam words list");
        super.clickOnListingRecord(index);
        super.fillInputBoxIn("word", word);
        super.clickOnSubmitButton("Update");
        super.checkColumnHasValue(index, 0,  word);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/administrator.word/negativeUpdateWord.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(20)
    public void NegativeUpdateWord(final String word, final Integer index) {
        super.signIn("administrator", "administrator");
        super.clickOnMenu("Administrator", "Spam words list");
        super.clickOnListingRecord(index);
        super.fillInputBoxIn("word", word);
        super.clickOnSubmitButton("Update");
        super.checkErrorsExist();
    }

}