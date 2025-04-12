package Tests;
import Pages.*;
import Pages.MyUserPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@DisplayName("Класс с тестами c авторизацией")
public class TestsWithAuthorization extends TestsAuthSetupTestBase{
    AfterAuthorizationHomePage afterAuthorizationHomePage;
    private final String LOGIN = "technopol63";
    private final String PASSWORD = "technopolisPassword";

    final String PATH = "static/photo/1.jpg";

    @DisplayName("Регистрация в системе с логоном и паролем.")
    @BeforeEach
    public void authorizationWithLoginAndPassword () {
        authorizationPage.setLogin(LOGIN);
        authorizationPage.setPassword(PASSWORD);
        authorizationPage.clickEnterButton();
        afterAuthorizationHomePage = new AfterAuthorizationHomePage();
        Assertions.assertTrue(afterAuthorizationHomePage.isMenuDisplayed(), "Меню должно отображаться после авторизации");

    }

    @Nested
    @DisplayName("Тесты медиа")
    class MediaTests {
        @DisplayName("Добавление фото")
        @Tag("PhotoUpload")
        @Tag("UserProfile")
        @Tag("MediaManagement")
        @ParameterizedTest
        @ValueSource(strings = {"static/photo/1.jpg", "static/photo/2.jpg", "static/photo/4.jpg"})
        public void testAddPhoto(String path) {
            AfterAuthorizationHomePage afterAuthorizationHomePage = new AfterAuthorizationHomePage();
            MyUserPage myUserPage = afterAuthorizationHomePage.goToProfile();
            myUserPage.clickNewPhoto();
            String result1 = myUserPage.countElements();

            afterAuthorizationHomePage.goToProfile();
            File imageFile = new File(path);
            myUserPage.addButtonPhoto(imageFile);
            myUserPage.clickNewPhoto();
            String result2 = myUserPage.waitForCountChange(result1);
            int number1 = myUserPage.parsePhotoCount(result1) + 1;
            int number2 = myUserPage.parsePhotoCount(result2);
            Assertions.assertEquals(number1, number2);

        }

        @Disabled("Тест требует доработки")
        @Tag("PhotoUpload")
        @Tag("UserProfile")
        @Tag("MediaManagement")
        @DisplayName("Добавление обложки на странице пользователя")
        public void testAddCover()  {
            AfterAuthorizationHomePage afterAuthorizationHomePage = new AfterAuthorizationHomePage();
            MyUserPage myUserPage = afterAuthorizationHomePage.goToProfile();
            File imageFile = new File(PATH);
            myUserPage.addPhotoCover(imageFile);

            afterAuthorizationHomePage.goToHome();
            afterAuthorizationHomePage.goToProfile();

            String timePhotoAdd = myUserPage.getTimePhotoCover();

            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            List<String> acceptableTimes = List.of(
                    currentTime.minusMinutes(2).format(formatter),
                    currentTime.minusMinutes(1).format(formatter),
                    currentTime.format(formatter),
                    currentTime.plusMinutes(1).format(formatter),
                    currentTime.plusMinutes(2).format(formatter)
            );

            Assertions.assertTrue(acceptableTimes.contains(timePhotoAdd),
                    "Время добавления обложки должно совпадать с текущим временем +- 2 минуты. " +
                            "Фактическое время: " + timePhotoAdd + ", допустимые значения: " + acceptableTimes);
        }
    }

    @DisplayName("Проверка имени пользователя по имени на странице пользователя и имена пользователя в меню")
    @Test
    public void testNameVerification(){
        AfterAuthorizationHomePage afterAuthorizationHomePage = new AfterAuthorizationHomePage();
        MyUserPage myUserPage = afterAuthorizationHomePage.goToProfile();
        boolean Result = myUserPage.checkUser();
        Assertions.assertTrue(Result);
    }
}
