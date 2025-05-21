package Tests;

import Pages.HomePage;
import Pages.PhotoPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;

public class TestsPhoto extends TestAuthorizedSetup {
    @DisplayName("Добавление фото")
    @Tag("PhotoUpload")
    @Tag("UserProfile")
    @Tag("MediaManagement")
    @ParameterizedTest
    @ValueSource(strings = {"static/photo/1.jpg", "static/photo/2.jpg", "static/photo/4.jpg"})
    public void testAddPhoto(String path){
        HomePage homePage = new HomePage();
        File imageFile = new File(path);
        Assertions.assertTrue(imageFile.exists(), "Файл " + path + " не найден");

        PhotoPage photoPage = homePage
                .openPhoto();

        int result1 = photoPage
                .checkPage()
                .getCountPhotoText();

        photoPage.addNewPhoto(imageFile).waitForPhotoCountToIncrease(result1);
        int result2 = photoPage.getCountPhotoText();
        Assertions.assertEquals(result1 + 1 , result2, "Количество фото не изменилось");
    }

}
