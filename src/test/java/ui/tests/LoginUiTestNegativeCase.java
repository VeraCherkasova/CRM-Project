package ui.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginUiTestNegativeCase extends BaseUiTest {

    @Test
    @DisplayName("Заполнение формы авторизации с ошибкой: ввод неверного логина и пароля")
    public void testLoginNegativeCase() {
        open("", LoginPage.class)
                .setLogin("dlkrhfldjs")
                .setPassword("pohp[goj[og")
                .clickLogInMistake()
                .checkErrorText("Wrong username/password");
    }
}
