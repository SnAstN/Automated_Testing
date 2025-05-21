package Pages;

import ElementPage.NavigationMenu;
import ValueObject.UserDate;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage extends LoadablePage<ProfilePage> {
    private final NavigationMenu navigationMenu;
    private UserDate userDate = new UserDate();
    private final By NAME_PROFILE = By.xpath("//h1[contains(@class,  'user-profile')]");
    private final By PHOTO_PROFILE = By.xpath("//div[contains(@class,  'stub-img')]");

    private final By ABOUT_ME_LABEL = By.xpath("//div[contains(@data-l, 'textField')]");

    private final By ABOUT_ME_LABEL_INPUT = By.xpath("//textarea[contains(@class, 'text-field_editor')]");

    private final By SAVE_BUTTON_ABOUT_ME = By.xpath("//button[contains(@data-l, 'textField-save')]");


    private final String NEW_PHOTO_BUTTON = "//a[contains(@data-l, 'userPhotos') and contains(@class,'navMenu')]";

    private final String PHOTO_COUNT = "//a[contains(@data-l, 'userPhotos') and contains(@class,'navMenu')]/span";

    public ProfilePage(NavigationMenu navigationMenu) {
        this.navigationMenu = navigationMenu;
    }


    public String textProfileButtonNavigation(){
        return navigationMenu.textProfileButton();
    }
    public ProfilePage clearTextAboutMe() {
        $(ABOUT_ME_LABEL)
                .shouldBe(visible.because("Поле о себе не отображается"))
                .shouldBe(enabled.because("Поле о себе не доступна для клика"))
                .click();
        $(ABOUT_ME_LABEL_INPUT)
                .shouldBe(visible.because("Поле о себе для ввода не отображается"))
                .sendKeys(Keys.BACK_SPACE);
        return this;
    }

    public ProfilePage setTextAboutMe(String text) {
        $(ABOUT_ME_LABEL_INPUT).setValue(text);
        return this;
    }

    public ProfilePage saveTextAboutMe() {
        $(SAVE_BUTTON_ABOUT_ME)
                .shouldBe(visible.because("Кнопка save не отображается"))
                .shouldBe(enabled.because("Кнопка save не доступна для клика"))
                .click();
        return this;
    }


    public String getTextAboutMe(){
        return $(ABOUT_ME_LABEL)
                .shouldBe(visible.because("Поле о себе не отображается"))
                .shouldBe(enabled)
                .getText();
         }

    public void clickNewPhoto(){
        $(By.xpath(NEW_PHOTO_BUTTON)).click();
    }

    public String countElements(){
        return $(By.xpath(PHOTO_COUNT)).getText();
    }

    public ProfilePage checkPage() {
        $(NAME_PROFILE).shouldBe(visible.because("Имя пользователя не отображается на странице"));
        $(PHOTO_PROFILE).shouldBe(visible.because("Иконка фото пользователя не отображается на странице"));
        return this;
    }
    public String getFullName(){
        String name = $(NAME_PROFILE).shouldBe(visible.because("Имя пользователя не отображается на странице")).getText();
        userDate.setFullName(name);
        return userDate.getFullName();
    }

}
