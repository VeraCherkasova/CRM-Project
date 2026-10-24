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

public class UploadFileXlsUiTest extends BaseUiTest {

    File fileUploadDoc = new File("src/test/resources/upload/Clinics.xlsx");

    @Order(2)
    @Run
    @ExternalId("171")
    @WorkItemIds("171")
    @Test
    @DisplayName("UI. Загрузка эксель файла")
    @ru.testit.annotations.DisplayName("UI. Загрузка эксель файла")
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
