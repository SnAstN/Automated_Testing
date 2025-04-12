package Pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FindPage {
    public final String GO_BACK_BUTTON = "//a[@data-l='t,logo']";

    public void goBackPicture(){
        $(By.xpath(GO_BACK_BUTTON)).click();
    }

}