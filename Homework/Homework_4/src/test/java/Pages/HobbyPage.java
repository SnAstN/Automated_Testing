package Pages;

import static com.codeborne.selenide.Selenide.back;

public class HobbyPage {
    public final String DESIRED_URL_HOBBY = "https://ok.ru/hobby";
    public boolean checkUrlHobby(String currentUrl){
        return DESIRED_URL_HOBBY.equals(currentUrl);
    }

    public void goBack() {
        back();
    }
}
