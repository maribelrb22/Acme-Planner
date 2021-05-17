package acme.testing.authenticated.consumer;

import acme.testing.AcmePlannerTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class UpdateTests extends AcmePlannerTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/authenticated.consumer/positive-update.csv", encoding = "utf-8", numLinesToSkip = 1)    @Order(10)
    public void positiveUpdateConsumer(final String company, final String sector) {
        super.signUp("Consumer1", "Consumer1", "Consumer", "One", "Consumer@mail.com", "");
        super.signIn("Consumer1", "Consumer1");
        super.clickOnMenu("Account", "Become a consumer");
        super.fillInputBoxIn("company", "Compañia 1");
        super.fillInputBoxIn("sector", "Caballeria");
        super.clickOnSubmitButton("Register");
        super.clickOnMenu("Account", "Consumer data");
        super.fillInputBoxIn("company", company);
        super.fillInputBoxIn("sector", sector);
        super.clickOnSubmitButton("Update");
        super.clickOnMenu("Account", "Consumer data");
        super.checkInputBoxHasValue("company", company);
        super.checkInputBoxHasValue("sector", sector);

        super.signOut();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/authenticated.consumer/negative-update.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(20)
    public void negativeUpdateConsumer(final String company, final String sector) {

        super.signIn("Consumer1", "Consumer1");

        super.clickOnMenu("Account", "Consumer data");

        super.fillInputBoxIn("company", company);
        super.fillInputBoxIn("sector", sector);
        super.clickOnSubmitButton("Update");
        super.checkErrorsExist();

        super.signOut();
    }
}
