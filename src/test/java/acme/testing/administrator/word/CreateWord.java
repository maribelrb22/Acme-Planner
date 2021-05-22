package acme.testing.administrator.word;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebElement;

import acme.testing.AcmePlannerTest;

//We are testing positive and negative spam words creation
public class CreateWord extends AcmePlannerTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/administrator.word/positiveCreateWord.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
    public void PositiveCreateWord(final String word, final int recordIndex) {
        super.signIn("administrator", "administrator");
        super.clickOnMenu("Administrator", "Add a spam word");
        super.fillInputBoxIn("word", word);
        super.clickOnSubmitButton("Add word");
        super.clickOnMenu("Administrator", "Spam words list");

        final List<WebElement> row = this.getListingRecord(recordIndex);
        Assertions.assertEquals(word, row.get(1).getText());
		
        super.signOut();
    }
    
    @ParameterizedTest
    @CsvFileSource(resources = "/administrator.word/negativeCreateWord.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(20)
    public void NegativeCreateWord(final String word) {
        super.signIn("administrator", "administrator");
        super.clickOnMenu("Administrator", "Add a spam word");
        super.fillInputBoxIn("word", word);
        super.clickOnSubmitButton("Add word");
        super.checkErrorsExist();
    }
}
