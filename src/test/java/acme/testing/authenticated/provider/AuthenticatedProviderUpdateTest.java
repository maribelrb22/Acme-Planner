package acme.testing.authenticated.provider;

import acme.testing.AcmeWorkPlansTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class AuthenticatedProviderUpdateTest extends AcmeWorkPlansTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/authenticated/provider/positive-update.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
    public void positiveUpdateConsumer(final String company, final String sector) {
        super.signUp("Provider1", "Provider1", "Provider1", "One", "Provider@mail.com", "");
        super.signIn("Provider1", "Provider1");
        super.clickOnMenu("Account", "Become a provider");
        super.fillInputBoxIn("company", "Compañia 1");
        super.fillInputBoxIn("sector", "Caballeria");
        super.clickOnSubmitButton("Register");
        super.clickOnMenu("Account", "Provider data");
        super.fillInputBoxIn("company", company);
        super.fillInputBoxIn("sector", sector);
        super.clickOnSubmitButton("Update");
        super.clickOnMenu("Account", "Provider data");
        super.checkInputBoxHasValue("company", company);
        super.checkInputBoxHasValue("sector", sector);

        super.signOut();
    }

    // This test case check that the data consumer can't be modified because of empty fields
    @ParameterizedTest
    @CsvFileSource(resources = "/authenticated/provider/negative-update.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(20)
    public void negativeUpdateConsumer(final String company, final String sector) {

        super.signIn("Provider1", "Provider1");

        super.clickOnMenu("Account", "Provider data");

        super.fillInputBoxIn("company", company);
        super.fillInputBoxIn("sector", sector);
        super.clickOnSubmitButton("Update");
        super.checkErrorsExist();

        super.signOut();
    }
}

