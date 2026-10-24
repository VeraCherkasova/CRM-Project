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
import utils.PDFUtils;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class DownloadPdfUiTest extends BaseUiTest {

    @Order(3)
    @Run
    @ExternalId("166")
    @WorkItemIds("166")
    @Test
    @DisplayName("UI. Скачивание пдф файла")
    @ru.testit.annotations.DisplayName("UI. Скачивание пдф файла")
    @Step
    public void test() throws IOException {
        open("", LoginPage.class)
                .setLogin(login)
                .setPassword(password)
                .clickLogIn();

        File file = open("#Document", DocumentsPage.class)
                .downloadDocByType("pdf");

        System.out.println("File path: " + file.getAbsolutePath());
        System.out.println("File size: " + file.length());
        String text = PDFUtils.getText(file);
        System.out.println("PDF text = [" + text + "]");
        System.out.println("Contains = " + text.contains("Урок 1. Первый автотест на Selenide"));

        Assertions.assertTrue(PDFUtils.getText(file).contains("Урок 1. Первый автотест на Selenide"));
    }
}
