package Tests;

import Pages.GroupPageArray;
import Pages.FindPage;
import Pages.GroupOnePage;
import Pages.HobbyPage;
import com.codeborne.selenide.WebDriverRunner;

import java.util.stream.Stream;
import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;


@DisplayName("Класс с тестами без авторизации")
public class TestWithoutAuthorization extends TestsAuthSetupTestBase{
    private final String REGISTER_1 = "Зарегистрироваться";
    private final String REGISTER_2 = "Register";
    private final String PASSWORD_1 = "Пароль";
    private final String PASSWORD_2 = "Password";


    @Test
    @DisplayName("Проверка смены языка с русского на английский")
    @Tag("Localization")
    public void testChangeEnglishLanguage()  {
        String text1 = authorizationPage.getRegisterButtonText();
        String text2 = authorizationPage.getPasswordLabelText();

        authorizationPage.changeLanguageToEnglish();

        Assertions.assertAll(
                () -> Assertions.assertEquals(REGISTER_1, text1),
                () -> Assertions.assertEquals(PASSWORD_1, text2)
        );
        String text3 = authorizationPage.getRegisterButtonText();
        String text4 = authorizationPage.getPasswordLabelText();

        Assertions.assertAll(
                () -> Assertions.assertEquals(REGISTER_2, text3),
                () -> Assertions.assertEquals(PASSWORD_2, text4)
        );
    }


    @ParameterizedTest
    @ValueSource(strings = {"Snezhanna A", "Увлечения", "Text"})
    @DisplayName("Проверка работы кнопки перехода на главную страницу")
    @Tag("Smoke")
    public void testGoBackButton(String searchText) {
        String currentUrl1 = WebDriverRunner.url();
        authorizationPage.dataEntry(searchText);
        new FindPage().goBackPicture();
        String currentUrl2 = WebDriverRunner.url();
        Assertions.assertEquals(currentUrl1, currentUrl2);
    }



    @DisplayName("Проверка того, что две разных способа зайти на вкладку увлечения работают одинаково")
    @Tag("UI_Test")
    @Tag("Feature_Search")
    @Tag("FunctionalTest")
    @Test
    public void testCheckingTheHobbyButton() {
        authorizationPage.hobbyFindClick();
        HobbyPage hobbyPage = new HobbyPage();
        Assertions.assertTrue(hobbyPage.checkUrlHobby(WebDriverRunner.getWebDriver().getCurrentUrl()));

        hobbyPage.goBack();
        authorizationPage.hobbyClick();
        Assertions.assertTrue(hobbyPage.checkUrlHobby(WebDriverRunner.getWebDriver().getCurrentUrl()));
    }



    @DisplayName("Проверка названия группы на глвной странице со всеми группами и на странице с самой группой")
    @Tag("Groups")
    @Tag("UI_Test")
    @Tag("FunctionalTest")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3}) // проверка у первых 4 групп
    @Timeout(5)  // Лимит времени выполнения
    public void testGroupName(int number) {
        authorizationPage.chooseButtonGroup();
        GroupPageArray groupPage = new GroupPageArray();
        String result1 = groupPage.getGroupName(number);
        GroupOnePage groupOnePage = groupPage.openGroupByIndex(number);
        String result2 = groupOnePage.getTitle();
        Assertions.assertEquals(result2, result1);
    }


    static Stream<String> searchTextProvider() {
        return Stream.of("normal text", "123", "   ", "!@#$%", "   text  ");
    }

    @DisplayName("Проверка работоспособности удаления введеной последовательности в поле поиска")
    @ParameterizedTest(name = "Test case: {0}")
    @MethodSource("searchTextProvider")
    @NullAndEmptySource
    @Tag("UI_Test")
    @Tag("Feature_Search")
    @Tag("SmokeTest")
    void testCleanSearchField(String inputText) {
        authorizationPage.dataEntryText(inputText);

        boolean isCleared = authorizationPage.clearTextLabelFind(inputText);
        String actualText = authorizationPage.getTextLabelFind();

        Assertions.assertAll(
                () -> {
                    if (inputText == null || inputText.isEmpty()){

                        Assertions.assertFalse(isCleared, "Для пустого ввода кнопка очистки не должна срабатывать");
                    } else {
                        Assertions.assertTrue(isCleared, "Для непустого ввода очистка должна быть успешной");
                    }
                },
                () -> {
                    Assertions.assertEquals("", actualText, "Поле должно быть пустым после очистки");
                }
        );
    }
}

