package Tests;

import Pages.GroupOneFullPage;
import Pages.GroupOneShortPage;
import Pages.GroupPage;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestsGroup extends TestAuthorizedSetup {

    @DisplayName("Проверка названия группы на глвной странице со всеми группами и на страницах с самой группой")
    @Tag("Groups")
    @Tag("UI_Test")
    @Tag("FunctionalTest")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    public void testGroupName(int number)  {
        GroupPage groupPage = homePage
                .openGroup();

        String result1 = groupPage
                .checkPage()
                .getGroupName(number);

        GroupOneShortPage groupOneShortPage = groupPage.openGroupByIndex(number);
        String result2 = groupOneShortPage.getTitle();
        Assertions.assertEquals(result1, result2, "название группы отличаются");

        GroupOneFullPage groupOneFullPage = groupOneShortPage.clickTitle();
        String result3 = groupOneFullPage.getTitle();
        Assertions.assertEquals(result2, result3,  "название группы отличаются");
    }


    @DisplayName("Проверка работоспособности возможности подписаться")
    @Tag("Groups")
    @Tag("UI_Test")
    @Tag("FunctionalTest")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 3})
    public void testGroupSubscribe(int index) throws Exception {
        GroupPage groupPage = homePage
                .openGroup();
        groupPage.checkPage();

        String url1 = WebDriverRunner.url();

        String initialButtonText = groupPage.getSubscribeButtonText(index);

        groupPage.clickGroupSubscribeButton(index);
        groupPage.waitForButtonTextChange(index, initialButtonText);

        String newButtonText = groupPage.getSubscribeButtonText(index);

        Assertions.assertNotEquals(initialButtonText, newButtonText,
                "Текст кнопки подписки должен измениться после нажатия");

        groupPage.clickGroupSubscribeButton(index);
        String url2 = WebDriverRunner.url();

        Assertions.assertNotEquals(url1, url2,
                "После того как пользователь подпишется кнопка не только должна изменить текст," +
                        "но и дать возмножность перейти в группу");
    }
}
