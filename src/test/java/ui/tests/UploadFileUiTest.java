package ui.tests;

import org.junit.jupiter.api.Test;
import ui.pages.DocumentsPage;
import ui.pages.LoginPage;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class UploadFileUiTest extends BaseUiTest {

    File fileUploadDoc = new File("src/test/resources/upload/Lesson_1_autotest_selenide.pdf");

    @Test
    public void test() throws IOException {
        open("", LoginPage.class)
                .setLogin(login)
                .setPassword(password)
                .clickLogIn();

        open("#Document", DocumentsPage.class)
                .clickCreateDocumentBtn()
                .uploadDoc(fileUploadDoc)
                .clickSaveBtn();
    }
}
