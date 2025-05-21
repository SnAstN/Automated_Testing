package Pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


// страница авторизации
public class AuthorizationPage extends LoadablePage<AuthorizationPage> {
    public final By LOGIN_LABEL = By.xpath("//input[@name='st.email']");
    public final By PASSWORD_LABEL = By.xpath("//input[@type = 'password']");
    public final By ENTER_BUTTON = By.xpath("//input[contains(@class, 'button-pro')]");
    public final By ERROR_MESSAGE  = By.xpath("//div[contains(@class, 'login_error')]");


    public AuthorizationPage setLogin(String login){
        $(LOGIN_LABEL).shouldBe(visible.because("Поле ввода телефона или адреса электронной почты не отображается")).setValue(login);
        return this;
    }

    public AuthorizationPage setPassword(String password){
        $(PASSWORD_LABEL).shouldBe(visible.because("Поле пароля не отображается")).setValue(password);
        return this;
    }

    public void clickEnterButton() {
        $(ENTER_BUTTON).shouldBe(visible.because("Кнопка входа не отображается")).click();

        // если будет ошибка ввода логина и пароля, то тест упадет на ensurePageLoaded(), то есть сообщение об
        // ошибке будет не корректным
        if ($(ERROR_MESSAGE).isDisplayed()) {
            throw new IllegalStateException("Ошибка авторизации: " + $(ERROR_MESSAGE).getText());
        }

    }


    @Override
    public AuthorizationPage checkPage() {
        $(LOGIN_LABEL).shouldBe(visible.because("Поле логина не отображается"));
        $(PASSWORD_LABEL).shouldBe(visible.because("Поле пароля не отображается"));
        $(ENTER_BUTTON).shouldBe(visible.because("Кнопка входа не отображается"));
        return this;
    }
}