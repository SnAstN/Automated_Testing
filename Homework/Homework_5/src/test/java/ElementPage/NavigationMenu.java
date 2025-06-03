package ElementPage;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class NavigationMenu {
    private final By GROUP_BUTTON = By.xpath("//a[@data-l ='t,userAltGroup']");
    private final By PROFILE_BUTTON = By.xpath("//a[@data-l='t,userPage']");
    private final By PHOTO_BUTTON = By.xpath("//a[@data-l='t,userPhotos']");

    private final By MENU = By.xpath( "//div[@class='navigation']");
    public void chooseButtonGroup(){
        $(GROUP_BUTTON)
                .shouldBe(visible.because("Кнопка группы в меню не отображается"))
                .shouldBe(enabled.because("Кнопка группы в меню не доступна для клика"))
                .click();
    }

    public By getContainer(){
        return MENU;
    }
    public void chooseButtonProfile(){
        $(PROFILE_BUTTON)
                .shouldBe(visible.because("Кнопка профиля в меню не отображается"))
                .shouldBe(enabled.because("Кнопка профиля в меню не доступна для клика"))
                .click();
        }

    public void chooseButtonPhoto(){
        $(PHOTO_BUTTON)
                .shouldBe(visible.because("Кнопка фото в меню не отображается"))
                .shouldBe(enabled.because("Кнопка фото в меню не доступна для клика"))
                .click();
        }

    public String textProfileButton(){
        return $(PROFILE_BUTTON)
                .shouldBe(visible.because("Кнопка профиля в меню не отображается"))
                .shouldBe(enabled.because("Кнопка профиля в меню не доступна для клика"))
                .getText();
    }
}