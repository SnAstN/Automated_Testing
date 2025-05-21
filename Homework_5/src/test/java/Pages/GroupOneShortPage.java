package Pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class GroupOneShortPage {
    private final By TITLE_GROUP = By.xpath("//a[contains(@class, 'group-main')]");

    public String getTitle(){
        return $(TITLE_GROUP).getText();
    }

    public GroupOneFullPage clickTitle (){
        $(TITLE_GROUP)
                .shouldBe(visible.because("Ссылка на полный профиль группы не отображается"))
                .shouldBe(enabled.because("Ссылка на полный профиль группы не доступна для клика"))
                .click();
        return new GroupOneFullPage();
    }

}
