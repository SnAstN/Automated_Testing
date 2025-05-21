package Pages;

import ElementPage.NavigationMenu;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GroupPage extends LoadablePage<GroupPage>{

    private final By GROUPS = By.cssSelector("a.group-detailed-card_name");
    private final By SUBSCRIBE_BUTTON =  By.xpath("//span/span[contains(@class, 'content')]");

    private ElementsCollection groupCards;
    private ElementsCollection subscribeArrays;


    public GroupPage() {
        groupCards = $$(GROUPS);
        subscribeArrays = $$(SUBSCRIBE_BUTTON);
    }

    public GroupPage checkPage() {
        $(GROUPS).shouldBe(visible.because("Посты не отображаются"));
        return this;
    }

    public String getGroupName(int index) {
        return groupCards.get(index).shouldBe(visible.because("Карточка группы не отображается")).getText();
    }

    public GroupPage clickGroupSubscribeButton(int index) {
        subscribeArrays.get(index).shouldBe(visible.because("Кнопка подписаться")).click();
        return this;
    }
    public String getSubscribeButtonText(int index) {
        return subscribeArrays.get(index)
                .shouldBe(visible.because("Кнопка подписки не отображается"))
                .shouldBe(visible.because("Кнопка подписки не отображается"))
                .getText();
    }

    public GroupPage waitForButtonTextChange(int index, String initialText) {
        subscribeArrays.get(index)
                .shouldBe(visible)
                .shouldNotHave(text(initialText))
                .shouldNotHave(attribute("disabled"));
        return this;
    }

    public GroupOneShortPage openGroupByIndex(int index) {
        groupCards.get(index)
                .shouldBe(visible.because("Карточка группы не отображается"))
                .shouldBe(enabled.because("Карточка группы для клика"))
                .click();
        return new GroupOneShortPage();
    }

}

