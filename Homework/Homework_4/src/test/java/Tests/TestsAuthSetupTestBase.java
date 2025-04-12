package Tests;

import Pages.AuthorizationPage;


import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static com.codeborne.selenide.Selenide.closeWebDriver;
@DisplayName("Класс с тестами, выполняемыми перед всеми тестами и после них")
public class TestsAuthSetupTestBase {
    private final String PATH_MAIN = "https://ok.ru/dk?st.cmd=anonymMain";
    public AuthorizationPage authorizationPage;
    @BeforeEach
    public void setUp(){
        authorizationPage = new AuthorizationPage(PATH_MAIN);
        authorizationPage.open();
    }
    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
