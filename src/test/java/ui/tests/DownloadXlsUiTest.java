package ui.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ui.pages.DocumentsPage;
import ui.pages.LoginPage;
import utils.XlsUtils;

import java.io.File;

import static com.codeborne.selenide.Selenide.open;

public class DownloadXlsUiTest extends BaseUiTest {

    @Test
    public void test() {
            open("", LoginPage.class)
                    .setLogin(login)
                    .setPassword(password)
                    .clickLogIn();

            File file = open("#Document", DocumentsPage.class)
                    .downloadDoc();

        String text = XlsUtils.getText(file);
        Assertions.assertTrue(text.contains("клиника"));




    }

}
