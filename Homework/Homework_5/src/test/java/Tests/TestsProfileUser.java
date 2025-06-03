package Tests;

import Pages.ProfilePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TestsProfileUser extends TestAuthorizedSetup {
    public static Stream<Arguments> getTestData() {
        return Stream.of(
                Arguments.of("text text text    ", "text text text"),
                Arguments.of("result", "result")
        );
    }

    @DisplayName("Добавление информации обо мне на странице пользователя, " +
            "перед добавлением будет удалено то, что было написано раньше в этом поле")
    @Tag("UI")
    @Tag("Positive test")
    @Tag("UserProfile")
    @Tag("basic functionality")
    @ParameterizedTest
    @MethodSource("getTestData")
    public void testAboutMeInformation(String input, String expected) {
        ProfilePage profilePage = homePage.openProfile();
        profilePage.checkPage()
                .clearTextAboutMe()
                .setTextAboutMe(input)
                .saveTextAboutMe();
        Assertions.assertEquals(expected, profilePage.getTextAboutMe());
    }


    @DisplayName("Проверка имени пользователя")
    @Tag("UI")
    @Tag("UserProfile")
    @Tag("basic functionality")
    @Test
    public void testCheckNameUser() {
        ProfilePage profilePage = homePage.openProfile();
        String nameUser1 = profilePage.checkPage()
                .textProfileButtonNavigation();
        String nameUser2 = profilePage.getFullName();

        Assertions.assertEquals(nameUser1, nameUser2, "имя пользователя в toolbar и профиле совпадает");
    }
}
