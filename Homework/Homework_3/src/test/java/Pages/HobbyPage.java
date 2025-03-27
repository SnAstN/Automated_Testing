package Pages;

import static com.codeborne.selenide.Selenide.back;

public class HobbyPage implements BasePage{
    public AuthorizationPage goBack() {
        back();
        return new AuthorizationPage();
    }

}
