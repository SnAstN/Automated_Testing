package Pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FindPage {
    public final String goBackButton = "//a[@data-l='t,logo']";

    public void goBackPicture(){
        $(By.xpath(goBackButton)).click();
    }

}