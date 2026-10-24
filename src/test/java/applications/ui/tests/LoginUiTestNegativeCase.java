package applications.ui.tests;

import annotations.Run;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import applications.ui.pages.LoginPage;
import ru.testit.annotations.ExternalId;
import ru.testit.annotations.WorkItemIds;

import static com.codeborne.selenide.Selenide.open;

public class LoginUiTestNegativeCase extends BaseUiTest {

    @Run
    @ExternalId("169")
    @WorkItemIds("169")
    @Test
    @DisplayName("UI. Заполнение формы авторизации с ошибкой: ввод неверного логина и пароля")
    @ru.testit.annotations.DisplayName("UI. Заполнение формы авторизации с ошибкой: ввод неверного логина и пароля")
    public void testLoginNegativeCase() {
        open("", LoginPage.class)
                .setLogin("dlkrhfldjs")
                .setPassword("pohp[goj[og")
                .clickLogInMistake()
                .checkErrorText("Wrong username/password");
    }
}
