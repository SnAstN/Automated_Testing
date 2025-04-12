package Tests;

import Pages.*;

import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class Tests {
    private final String PATH_MAIN = "https://ok.ru/dk?st.cmd=anonymMain";
    private final String REGISTER_1 = "Зарегистрироваться";
    private final String REGISTER_2 = "Register";
    private final String PASSWORD_1 = "Пароль";
    private final String PASSWORD_2 = "Password";
    private final String SEARCH_TEXT = "Snezhanna A";
    private final String SOMETHING_TEXT = "something text something text";

    int NUMBER_PAGE = 0;

    private AuthorizationPage authorizationPage;

    @Before
    public void setUp(){
        authorizationPage = new AuthorizationPage(PATH_MAIN);
        authorizationPage.open();
    }


    // проверка смены языка на English
    @Test
    public void testChangeEnglishLanguage()  {
        String text1 = authorizationPage.getRegisterButtonText();
        String text2 = authorizationPage.getPasswordLabelText();

        authorizationPage.changeLanguageToEnglish();

        Assertions.assertTrue(text1.equals(REGISTER_1) && text2.equals(PASSWORD_1));

        String text3 = authorizationPage.getRegisterButtonText();
        String text4 = authorizationPage.getPasswordLabelText();

        Assertions.assertTrue(text3.equals(REGISTER_2) && text4.equals(PASSWORD_2));
    }


    // проверка работы кнопки перехода на главную (регистрации) страницу.
    // что-то ищем в поске и возвращаемся назад
    @Test
    public void testGoBackButton() {
        String currentUrl1 = WebDriverRunner.getWebDriver().getCurrentUrl();
        authorizationPage.dataEntry(SEARCH_TEXT);
        FindPage findPage = new FindPage();
        findPage.goBackPicture();
        String currentUrl2 = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assertions.assertEquals(currentUrl1, currentUrl2);
    }


    // проверка того, что две разных способа зайти на вкладку увлечения работают одинаково
    @Test
    public void testCheckingTheHobbyButton() {

        authorizationPage.hobbyFindClick();
        HobbyPage hobbyPage = new HobbyPage();
        Assertions.assertTrue(hobbyPage.checkUrlHobby(WebDriverRunner.getWebDriver().getCurrentUrl()));

        hobbyPage.goBack();
        authorizationPage.hobbyClick();
        Assertions.assertTrue(hobbyPage.checkUrlHobby(WebDriverRunner.getWebDriver().getCurrentUrl()));


    }

    // проверка работоспособности удаления введеной последовательности в поле поиска
    @Test
    public void testCleanSearchField() {
        authorizationPage.dataEntryText(SOMETHING_TEXT);
        authorizationPage.clearTextLabelFind();
        String result = authorizationPage.getTextLabelFind();
        Assertions.assertTrue(result.equals("") || result == null);
    }

    // проверка названия группы на галвной странице со всеми группами и на странице с самой группой.
    @Test
    public void testGroupName() {
        authorizationPage.chooseButtonGroup();
        GroupPageArray groupPage = new GroupPageArray();
        String result1 = groupPage.getGroupName(NUMBER_PAGE);
        GroupOnePage groupOnePage = groupPage.openGroupByIndex(NUMBER_PAGE);
        String result2 = groupOnePage.getTitle();
        Assertions.assertEquals(result2, result1);

    }

    @After
    public void tearDown() {
        closeWebDriver();
    }
}
