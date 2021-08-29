package acme.testing.administrator.word;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebElement;

import acme.testing.AcmeWorkPlansTest;

public class TestDeleteWord extends AcmeWorkPlansTest {
	
	//Pruebo que al borrar una spam word, la que sustituye su posicion en la lista no es la misma que la borrada,
	//y asi comprobar que se elimina correctamente. Siempre es la posición 0 de la lista ya que se van actualizando sus
	//posiciones una vez eliminada una palabra.
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/positiveList.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveDeleteShow(final int index, final String word) {
		super.signIn("administrator","administrator");
		super.clickOnMenu("Administrator", "Spam words list");
		
		super.checkColumnHasValue(0, 0, word);
		super.clickOnListingRecord(0);
		
		super.clickOnSubmitButton("Delete");
		
		final List<WebElement> row = this.getListingRecord(0);
		Assertions.assertNotSame(row.get(1).getText(),word);

		super.signOut();
	}
}
