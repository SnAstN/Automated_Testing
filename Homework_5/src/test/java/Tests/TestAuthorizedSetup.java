package Tests;

import Pages.*;
import ValueObject.Credentials;

import org.junit.jupiter.api.*;


@DisplayName("Класс c авторизацией")
public class TestAuthorizedSetup extends TestBase {
    HomePage homePage;
    public final static String userName = "technopol63";
    public final static String password = "technopolisPassword";
    private static final Credentials VALID_CREDENTIALS =
            new Credentials (userName, password);

    @DisplayName("Регистрация в системе с логоном и паролем.")
    @BeforeEach
    public void authorizationWithLoginAndPassword () {
        authorizationPage
                .setLogin(VALID_CREDENTIALS.getLOGIN())
                .setPassword(VALID_CREDENTIALS.getPASSWORD())
                .clickEnterButton();

        homePage = new HomePage();
        homePage.checkPage();
        Assertions.assertTrue(homePage
                .isMenuDisplayed(), "Меню должно отображаться после авторизации");
    }

}