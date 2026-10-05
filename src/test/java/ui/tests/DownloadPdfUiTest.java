package ui.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ui.pages.DocumentsPage;
import ui.pages.LoginPage;
import utils.PDFUtils;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class DownloadPdfUiTest extends BaseUiTest {

    @Test
    public void test() throws IOException {
        open("", LoginPage.class)
                .setLogin(login)
                .setPassword(password)
                .clickLogIn();

        File file = open("#Document", DocumentsPage.class).downloadDoc();

        System.out.println("File path: " + file.getAbsolutePath());
        System.out.println("File size: " + file.length());
        String text = PDFUtils.getText(file);
        System.out.println("PDF text = [" + text + "]");
        System.out.println("Contains = " + text.contains("Урок 1. Первый автотест на Selenide"));

        Assertions.assertTrue(PDFUtils.getText(file).contains("Урок 1. Первый автотест на Selenide"));
//        Assertions.assertEquals(9, PDFUtils.getCountPage(file));
    }

}
