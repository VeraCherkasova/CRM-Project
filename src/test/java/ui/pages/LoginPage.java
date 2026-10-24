package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private final SelenideElement logInBtn = $("#btn-login");
    private final SelenideElement loginInp = $("#field-userName");
    private final SelenideElement passwordInp = $("#field-password");
    private final SelenideElement errorText = $x("//*[text()='Wrong username/password']");

    public LoginPage() {
        loginInp.should(Condition.visible.because("Страница не открылась"));
    }

    @Step("Открываем страницу - 'Логин'")
    public LoginPage open() {
        Selenide.open("");
        return Selenide.page(new LoginPage());
    }

    @Step("Заполнить поле 'Логин' - [{1}]")
    public LoginPage setLogin(String login) {
        loginInp.val(login);
        return this;
    }

    @Step("Заполнить поле 'Пароль' - [{1}]")
    public LoginPage setPassword(String password) {
        passwordInp.val(password);
        return this;
    }

    @Step("Нажать на кнопку 'Log In'")
    public MainPage clickLogIn() {
        logInBtn.click();
        return Selenide.page(MainPage.class);
    }

    @Step("Нажать на кнопку 'Log In' после неверно введенного логина или пароля")
    public LoginPage clickLogInMistake() {
        logInBtn.click();
        return this;
    }

    @Step("Проверить ошибку после неверного ввода пароля и логина")
    public LoginPage checkErrorText(String expectedErrorText) {
        errorText.should(Condition.visible);
        Assertions.assertTrue(errorText.getText().contains(expectedErrorText), "Поле c ошибкой не содержит ожидаемый текст: " + expectedErrorText);
        return this;
    }
}
