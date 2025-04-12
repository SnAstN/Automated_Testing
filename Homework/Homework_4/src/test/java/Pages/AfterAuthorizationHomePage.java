package Pages;

import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


@DisplayName("Описание основных элементов для всех страниц")
public class AfterAuthorizationHomePage {
    private final String MENU = "//div[@class='nav-side __navigation __user-main']";

    private final String  PROFILE_BUTTON  = "//a[@data-l='t,userPage']";

    private final String HOME_BUTTON = "//a[@data-l='t,userMain' and @class='nav-side_i  __with-ic __with-new-icons']";


    public boolean isMenuDisplayed() {
        try {
            $(By.xpath(MENU)).shouldBe(visible);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public MyUserPage goToProfile(){
        $(By.xpath(PROFILE_BUTTON)).click();
        return new MyUserPage();
    }
    public void goToHome(){
        $(By.xpath(HOME_BUTTON)).click();
    }

}
