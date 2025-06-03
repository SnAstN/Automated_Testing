package Pages;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PhotoPage extends LoadablePage<PhotoPage> {
    private final By FIND_PHOTO = By.xpath("//input[@id = 'field_query']");
    private final By COUNT_PHOTO = By.xpath("//div[@data-l='t,info']/div");
    private final By PHOTO_CLICK_BUTTON = By.xpath("//span[contains(@data-l, 'upload-new-photo')]");

    private final By PHOTO_ADD_BUTTON = By.xpath("//input[contains(@accept, 'image')]");

    public int getCountPhotoText(){
         String result = $(COUNT_PHOTO)
                .shouldBe(visible.because("Поле количества фото не отображается"))
                .shouldBe(enabled.because("Поле количества фото не доступна для клика"))
                .getText();

        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(result);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        } else {
            return 0;
        }
    }

    public PhotoPage addNewPhoto(File imageFile)  {
        $(PHOTO_CLICK_BUTTON)
                .shouldBe(visible.because("Кнопка добавления фото не отображается"))
                .shouldBe(enabled.because("Кнопка добавления фото не доступна для клика"))
                .click();
        $(PHOTO_ADD_BUTTON).uploadFile(imageFile);
        return this;
    }


    public PhotoPage waitForPhotoCountToIncrease(int initialCount) {
        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(10))
                .until(driver -> (getCountPhotoText() > initialCount));
        return this;
    }


    @Override
    public PhotoPage checkPage() {
        $(FIND_PHOTO).shouldBe(visible.because("Поле поиска не отображаются"));
        $(COUNT_PHOTO).shouldBe(visible.because("Поле количества фото не отображаются"));
        return this;
    }
}
