package acme.testing.authenticated.provider;

import acme.testing.AcmeWorkPlansTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class AuthenticatedProviderCreateTest extends AcmeWorkPlansTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/authenticated/provider/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
    public void negativeBecomeProvider(final String username, final String password, final String name, final String surname, final String email, final String company, final String sector, final String phone) {
        super.signUp(username, password, name, surname, email, phone);
        super.signIn(username, password);
        super.clickOnMenu("Account", "Become a provider");
        super.fillInputBoxIn("company", company);
        super.fillInputBoxIn("sector", sector);
        super.clickOnSubmitButton("Register");
        super.checkErrorsExist();
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/sign-up/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(20)
    public void positiveBecomeProvider(final String username, final String password, final String name, final String surname, final String email, final String company, final String sector, final String phone) {
        super.signIn(username, password);
        super.clickOnMenu("Account", "Become a provider");
        super.fillInputBoxIn("company", company);
        super.fillInputBoxIn("sector", sector);

        super.clickOnSubmitButton("Register");
        super.clickOnMenu("Account", "Provider data");
        super.checkInputBoxHasValue("company", company);
        super.checkInputBoxHasValue("sector", sector);

    }
}
