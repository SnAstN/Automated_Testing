package Tests;

import Pages.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;


public class TestPage  {
    private final String pathMain = "https://ok.ru/dk?st.cmd=anonymMain";
    AuthorizationPage authorizationPage = new AuthorizationPage();

    @Before
    public void setUp() throws Exception{
        Configuration.browser = "chrome";
        Configuration.browserSize = "1080x1080";
        Configuration.headless = false;
        open(pathMain);
        Thread.sleep(1000);
    }

    // проверка смены языка на English
    @Test
    public void EnglishLanguage()  {
        String text1 = $(By.xpath(authorizationPage.ButtonRegister)).getText();
        String text2 = $(By.xpath(authorizationPage.LabelPassword)).getText();

        $(By.cssSelector(authorizationPage.LanguageButton)).click();
        $(By.cssSelector(authorizationPage.EnglishButton)).click();

        String text3 = $(By.xpath(authorizationPage.ButtonRegister)).getText();
        String text4 = $(By.xpath(authorizationPage.LabelPassword)).getText();

        Assertions.assertTrue(text3.equals("Register") &&
                text1.equals("Зарегистрироваться") &&
                text2.equals("Пароль") &&
                text4.equals("Password")
        );

    }

    // проверка работы кнопки перехода на главную (регистрации) страницу.
    // что-то ищем в поске и возвращаемся назад
    @Test
    public void GoBackButton() {

        String currentUrl1 = WebDriverRunner.getWebDriver().getCurrentUrl();

        $(By.xpath(authorizationPage.FindLabel)).click();
        $(By.xpath(authorizationPage.FindLabel)).setValue("Snezhanna A");
        $(By.xpath(authorizationPage.SearchLabel)).click();

        FindPage findPage = new FindPage();;
        $(By.xpath(findPage.goBackButton)).click();

        String currentUrl2 = WebDriverRunner.getWebDriver().getCurrentUrl();

        Assertions.assertTrue(currentUrl1.equals(currentUrl2));
    }


    // проверка того, что две разных способа зайти на вкладку увлечения работают одинаково
    @Test
    public void CheckingTheHobbyButton() {
        $(By.xpath(authorizationPage.FindLabel)).click();
        $(By.xpath(authorizationPage.HobbyButtonFind)).click();
        String currentUrl1 = WebDriverRunner.getWebDriver().getCurrentUrl();

        HobbyPage hobbyPage = new HobbyPage();
        hobbyPage.goBack();

        $(By.xpath(authorizationPage.HobbyButton)).click();
        String currentUrl2 = WebDriverRunner.getWebDriver().getCurrentUrl();

        Assertions.assertTrue(currentUrl1.equals(currentUrl2));
    }

    // проверка работоспособности удаления введеной последовательности в поле поиска
    @Test
    public void CleanSearchField() {
        $(By.xpath(authorizationPage.FindLabel)).click();
        $(By.xpath(authorizationPage.FindLabel)).setValue("Text Text Text");
        $(By.xpath(authorizationPage.ClearButton)).click();
        String result = $(By.xpath(authorizationPage.FindLabel)).getText();
        Assertions.assertTrue(result.equals(""));
    }

    @Test
    public void GroupName()  throws Exception{
        $(By.xpath(authorizationPage.FindLabel)).click();
        $(By.xpath(authorizationPage.GroupButton)).click();
        GroupPage groupPage = new GroupPage();
        String result1 = $(By.xpath(groupPage.Group1)).getAttribute("title");

        $(By.xpath(groupPage.Group1)).click();

        GroupOnePage groupOnePage = new GroupOnePage();
        String result2 = $(By.xpath(groupOnePage.onegroup)).getText();

        Assertions.assertTrue(result1.equals(result2));

    }
}
