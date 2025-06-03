package Pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class GroupOneFullPage {
    private final By TITLE_GROUP = By.xpath("//h1[contains(@class, 'group-name')]");

    public String getTitle(){
        return $(TITLE_GROUP).shouldBe(visible.because("Имя пользователя не отображается")).getText();
    }

}
