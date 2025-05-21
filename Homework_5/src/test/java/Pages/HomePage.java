package Pages;
import ElementPage.NavigationMenu;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

// Описание страницы лента - открывается сразу после авторизации
public class HomePage extends LoadablePage<HomePage> {
    private final NavigationMenu navigationMenu;
    private final By TOOLBAR = By.xpath("//div[contains(@class, 'toolbar_decor')]");



    public boolean isMenuDisplayed() {
        return $(navigationMenu.getContainer()).isDisplayed();
    }
    public HomePage() {
        this.navigationMenu = new NavigationMenu();
    }


    public NavigationMenu getNavigationMenu() {
        return navigationMenu;
    }

    public ProfilePage openProfile() {
        navigationMenu.chooseButtonProfile();
        return new ProfilePage(navigationMenu).checkPage();
    }

    public GroupPage openGroup(){
        navigationMenu.chooseButtonGroup();
        return new GroupPage();
    }

    public PhotoPage openPhoto(){
        navigationMenu.chooseButtonPhoto();
        return new PhotoPage();
    }

    @Override
    public HomePage checkPage() {
        $(navigationMenu.getContainer()).shouldBe(visible
                .because("Меню не отображается после авторизации"));
        $(TOOLBAR).shouldBe(visible
                .because("Toolbar не отображается"));
        return this;
    }
}
