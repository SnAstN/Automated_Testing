package Pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AuthorizationPage{
    private String pathMain;
    private final String LANGUAGE_BUTTON = "//div[@class='tico']";
    private final String ENGLISH_BUTTON = "//a[text() ='English']";
    private final String BUTTON_REGISTER = "//a[@class='button-pro __sec mb-3x __wide']";
    private final String LABEL_PASSWORD = "//label[@for='field_password']";

    private final String FIND_LABEL = "//input[@placeholder='Поиск в ОК']";

    private final String SEARCH_LABEL = "//a[@class='suggest-item__zd7xg' and text()='Показать все результаты']";

    private final String HOBBY_BUTTON_FIND = "//span[@class='suggest-title_text__4nym2' and text() ='Увлечения']";

    private final String  HOBBY_BUTTON = "//a[@href='/hobby']";
    private final String CLEAR_BUTTON = "//button[@class= 'clear__mofy2 input-right-icon__on39s']";

    private final String GROUP_BUTTON = "//*[@class='suggest-title_text__4nym2' and text()='Группы']";


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
    public void clearTextLabelFind(){
        $(By.xpath(CLEAR_BUTTON)).click();
    }
    public String getTextLabelFind(){
        return $(By.xpath(FIND_LABEL)).getText();
    }
    public void dataEntryText(String searchText){
        $(By.xpath(FIND_LABEL)).click();
        $(By.xpath(FIND_LABEL)).setValue(searchText);
    }

    public void chooseButtonGroup(){
        $(By.xpath(FIND_LABEL)).click();
        $(By.xpath(GROUP_BUTTON)).click();
    }

}
