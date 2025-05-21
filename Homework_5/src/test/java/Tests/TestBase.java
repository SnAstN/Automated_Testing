package Tests;

import Pages.AuthorizationPage;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@DisplayName("Класс с тестами, выполняемыми перед всеми тестами и после них")
public class TestBase {
    private final String PATH_MAIN = "https://ok.ru/dk?st.cmd=anonymMain";
    public AuthorizationPage authorizationPage;

    @BeforeEach
    public void setUp(){
        Selenide.open(PATH_MAIN);
        authorizationPage = new AuthorizationPage();
        authorizationPage.checkPage(); // проверка страницы на открытие и загрузку
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}