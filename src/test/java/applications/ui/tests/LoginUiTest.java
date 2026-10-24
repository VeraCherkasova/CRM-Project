package applications.ui.tests;

import annotations.Run;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import applications.ui.pages.LoginPage;
import ru.testit.annotations.ExternalId;
import ru.testit.annotations.Step;
import ru.testit.annotations.Title;
import ru.testit.annotations.WorkItemIds;

import static com.codeborne.selenide.Selenide.open;

public class LoginUiTest extends BaseUiTest {

    @Run
    @ExternalId("168")
    @WorkItemIds("168")
    @Test
    @DisplayName("UI. Заполнение формы авторизации: ввод логина и пароля")
    @ru.testit.annotations.DisplayName("UI. Заполнение формы авторизации: ввод логина и пароля")
    @Step
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
