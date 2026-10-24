package applications.ui.tests;

import annotations.Run;
import applications.ui.pages.DocumentsPage;
import applications.ui.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ru.testit.annotations.ExternalId;
import ru.testit.annotations.WorkItemIds;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class UploadFilePdfUiTest extends BaseUiTest {

    File fileUploadDoc = new File("src/test/resources/upload/Lesson_1_autotest_selenide.pdf");

    @Order(1)
    @Run
    @ExternalId("170")
    @WorkItemIds("170")
    @Test
    @DisplayName("UI. Загрузка пдф файла")
    @ru.testit.annotations.DisplayName("UI. Загрузка пдф файла")
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
