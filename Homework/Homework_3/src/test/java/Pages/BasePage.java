package Pages;

import static com.codeborne.selenide.Selenide.back;

public interface BasePage {

    default Object  goBack() {
        back();
        return new Object();
    }
}
