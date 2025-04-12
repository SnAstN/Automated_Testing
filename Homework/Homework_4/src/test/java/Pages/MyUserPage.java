package Pages;

import org.openqa.selenium.By;


import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

// страница пользователя
public class MyUserPage {

    private final String NAME_USERS ="//a/h1";

    private final String NAME_USERS_MENU = "//a[@data-l='t,userPage']";
    private final String NEW_PHOTO_BUTTON = "//a[@data-l='t,userPhotos' and @class='mctc_navMenuSec ellip-menu-item ']";

    private final String PHOTO_COUNT = "//div[contains(@class, 'count_') and @data-l='t,info']/div";

    private final String ADD_PHOTO = "//input[@type='file' and contains(@class, 'html5-upload-link __before-upload')]";

    private final String ADD_COVER_BUTTON = "//button[contains(@class, 'js-cover-install')]";
    private final String ADD_PHOTO_COVER = "//div[contains(@class, 'dropdown_cnt')]//input[@type='file']";


    private final String DIV_COVER_SAVE = "//button[@data-l='t,save']";
    public void clickNewPhoto(){
        $(By.xpath(NEW_PHOTO_BUTTON)).click();
    }


    public String countElements(){
        return $(By.xpath(PHOTO_COUNT)).getText();

    }
    public String waitForCountChange(String initialCount) {
        return $(By.xpath(PHOTO_COUNT))
                .shouldNotHave(text(initialCount))
                .shouldBe(visible)
                .getText();
    }



    public void addButtonPhoto(File imageFile){
        $(By.xpath(ADD_PHOTO)).uploadFile(imageFile);
    }
    public int parsePhotoCount(String countText) {
        if (countText == null || countText.contains("Нет")) {
            return 0;
        }
        try {
            return Integer.parseInt(countText.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void addPhotoCover(File imageFile){
        $(By.xpath(ADD_COVER_BUTTON)).click();
        $(By.xpath(ADD_PHOTO_COVER)).uploadFile(imageFile);
        $(By.xpath(DIV_COVER_SAVE)).click();
    }


    public boolean checkUser(){
        String res1 = $(By.xpath(NAME_USERS)).getText();
        String res2 = $(By.xpath(NAME_USERS_MENU)).getText();
        return res1.equals(res2);
    }

    public String getTimePhotoCover() {
        PostEverythingArray postEverythingArray = new PostEverythingArray();

        postEverythingArray.getAllPosts().shouldHave(sizeGreaterThan(0), Duration.ofSeconds(60));

        if (postEverythingArray.getPostCount() > 0) {
            return postEverythingArray.getPostTime(0);
        } else {
            throw new RuntimeException("Не найдено ни одного поста");
        }
    }



}
