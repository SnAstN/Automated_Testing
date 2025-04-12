package Pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AuthorizationPage{
    private String pathMain;
    private final String LANGUAGE_BUTTON = "//div[@class='tico']";
    private final String ENGLISH_BUTTON = "//a[text() ='English']";
    private final String BUTTON_REGISTER = "//a[@class='button-pro __sec mb-3x __wide']";
    private final String LABEL_PASSWORD = "//label[@for='field_password']";

    private final String FIND_LABEL = "//input[@class='input__prt1l __size-m__prt1l input__mofy2 input-field__on39s __right-icon__on39s __redesigned__on39s']";

    private final String SEARCH_LABEL = "//a[@class='suggest-item__zd7xg' and @data-l[contains(.,'SHOW_ALL')]]";

    private final String HOBBY_BUTTON_FIND = "//span[@data-icon-name='18/ico_hobbies_18']/following-sibling::span[@class='suggest-title_text__4nym2']";

    private final String  HOBBY_BUTTON = "//a[@href='/hobby']";
    private final String CLEAR_BUTTON = "//button[@class= 'clear__mofy2 input-right-icon__on39s']";

    private final String GROUP_BUTTON = "//span[@data-icon-name='ico_users_3_16']/following-sibling::span[@class='suggest-title_text__4nym2']";

    public final String LOGIN_LABEL = "//input[@name='st.email']";
    public final String PASSWORD_LABEL = "//input[@type = 'password']";
    public final String ENTER_BUTTON = "//input[@class = 'button-pro __wide']";


    public AuthorizationPage(String pathMain){

        this.pathMain = pathMain;
    }
    public void open() {
        Selenide.open(pathMain);
    }

    public String getRegisterButtonText(){
        return $(By.xpath(BUTTON_REGISTER)).getText();
    }
    public String getPasswordLabelText() {
        return $(By.xpath(LABEL_PASSWORD)).getText();
    }

    public void changeLanguageToEnglish() {
        $(By.xpath(LANGUAGE_BUTTON)).click();
        $(By.xpath(ENGLISH_BUTTON)).click();
    }



    public void dataEntry(String searchText){
        $(By.xpath(FIND_LABEL)).click();
        $(By.xpath(FIND_LABEL)).setValue(searchText);
        $(By.xpath(SEARCH_LABEL)).click();
    }
    public void hobbyFindClick() {
        $(By.xpath(FIND_LABEL)).click();

        $(By.xpath(HOBBY_BUTTON_FIND)).click();

    }

    public void hobbyClick(){
        $(By.xpath(HOBBY_BUTTON)).click();
    }


    public void chooseButtonGroup(){
        $(By.xpath(FIND_LABEL)).click();
        $(By.xpath(GROUP_BUTTON)).click();
    }

    public void setLogin(String login){
        $(By.xpath(LOGIN_LABEL)).setValue(login);
    }
    public void setPassword(String password){
        $(By.xpath(PASSWORD_LABEL)).setValue(password);
    }
    public void clickEnterButton() {
        $(By.xpath(ENTER_BUTTON)).click();
    }

    public void dataEntryText(String text) {
        $(By.xpath(FIND_LABEL)).setValue(text);
    }

    public boolean clearTextLabelFind(String text) {
        try {
            boolean isTextNotEmpty = text != null && !text.isEmpty();
            if (isTextNotEmpty){
                $(By.xpath(CLEAR_BUTTON)).shouldBe(visible).isDisplayed();
                $(By.xpath(CLEAR_BUTTON)).click();
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    public String getTextLabelFind() {
        return $(By.xpath(FIND_LABEL)).getValue();
    }
}
