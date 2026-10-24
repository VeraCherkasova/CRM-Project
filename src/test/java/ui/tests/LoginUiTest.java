package ui.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginUiTest extends BaseUiTest {

    @Test
    @DisplayName("Заполнение формы авторизации: ввод логина и пароля")
    public void testLogin() {
        open("", LoginPage.class)
                .setLogin(login)
                .setPassword(password)
                .clickLogIn()
                .clickTreeDotsBtn()
                .clickAdminBtn()
                .checkUserName("admin");
    }
}
