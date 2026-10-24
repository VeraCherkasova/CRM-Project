package applications.ui.tests;

import annotations.Run;
import applications.ui.pages.DocumentsPage;
import applications.ui.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ru.testit.annotations.ExternalId;
import ru.testit.annotations.Step;
import ru.testit.annotations.Title;
import ru.testit.annotations.WorkItemIds;
import utils.XlsUtils;

import java.io.File;

import static com.codeborne.selenide.Selenide.open;

public class DownloadXlsUiTest extends BaseUiTest {

    @Order(4)
    @Run
    @ExternalId("167")
    @WorkItemIds("167")
    @Test
    @DisplayName("UI. Скачивание эксель файла")
    @ru.testit.annotations.DisplayName("UI. Скачивание эксель файла")
    @Step
    public void test() {
            open("", LoginPage.class)
                    .setLogin(login)
                    .setPassword(password)
                    .clickLogIn();

            File file = open("#Document", DocumentsPage.class)
                    .downloadDocByType("xlsx");

        String text = XlsUtils.getText(file);
        Assertions.assertTrue(text.contains("клиника"));
    }
}
