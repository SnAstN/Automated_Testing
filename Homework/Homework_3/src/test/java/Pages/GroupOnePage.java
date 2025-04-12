package Pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class GroupOnePage {

    public final String TITLE_GROUP = "//h1[@class='group-name_h']";

    public String getTitle(){
        return $(By.xpath(TITLE_GROUP)).getText();
    }
}
