package applications.ui.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import ru.testit.annotations.Title;

import java.io.File;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class UserCreatePage {
    private final SelenideElement userNameInp = $("input[data-name='userName']");

    // Элементы для поля Name (выпадающий список)
    private final SelenideElement nameFieldContainer = $("div[data-name='name']");
    private final SelenideElement nameSelectizeInput = nameFieldContainer.$(".selectize-input");
    private String genderDropDown = ".option[data-value='%s']";

    private final SelenideElement firstNameInp = $("input[data-name='firstName']");
    private final SelenideElement lastNameInp = $("input[data-name='lastName']");
    private final SelenideElement uploadAvatarInput = $("input[type='file']");
    private final SelenideElement submitAvatarBtn = $("button[data-name='crop']");
    private final SelenideElement passwordInp = $("input[data-name='password']");
    private final SelenideElement confirmPasswordInp = $("input[data-name='passwordConfirm']");
    private final SelenideElement saveBtn = $("[data-name='save']");

    public UserCreatePage() {
        userNameInp.should(visible.because("Страница не открылась"));
    }

    @Step("Заполнить поле 'User Name'")
    @ru.testit.annotations.Step
    @Title("Заполнить поле 'User Name'")
    public UserCreatePage setUserName(String userName) {
        userNameInp.val(userName);
        return this;
    }

    @Step("Выбрать значение '{value}' в поле 'Name'")
    @ru.testit.annotations.Step
    @Title("Выбрать значение '{value}' в поле 'Name'")
    public UserCreatePage setName (String value) {
        nameSelectizeInput.click();
                $(genderDropDown.formatted(value))
                .shouldBe(visible, Duration.ofSeconds(10))
                .click();
        return this;
    }

    @Step("Заполнить поле 'First Name'")
    @ru.testit.annotations.Step
    @Title("Заполнить поле 'First Name'")
    public UserCreatePage setFirstName(String firstName) {
        firstNameInp.val(firstName);
        return this;
    }

    @Step("Заполнить поле 'Last Name'")
    @ru.testit.annotations.Step
    @Title("Заполнить поле 'Last Name'")
    public UserCreatePage setLastName (String lastName) {
        lastNameInp.val(lastName);
        return this;
    }

    @Step("Загрузить аватарку")
    @ru.testit.annotations.Step
    @Title("Загрузить аватарку")
    public UserCreatePage uploadAvatar(File file) {
        uploadAvatarInput.should(Condition.enabled).uploadFile(file);
        return this;
    }

    @Step("Подтвердить загрузку аватарки - кликнуть на кнопку 'Submit'")
    @ru.testit.annotations.Step
    @Title("Подтвердить загрузку аватарки - кликнуть на кнопку 'Submit'")
    public UserCreatePage clickSubmitAvatarBtn() {
        submitAvatarBtn.click();
        return this;
    }

    @Step("Установить пароль")
    @ru.testit.annotations.Step
    @Title("Установить пароль")
    public UserCreatePage setPassword (String password) {
        passwordInp.val(password);
        return this;
    }

    @Step("Подтвердить пароль")
    @ru.testit.annotations.Step
    @Title("Подтвердить пароль")
    public UserCreatePage setConfirmPassword(String password) {
        confirmPasswordInp.val(password);
        return this;
    }

    @Step("Нажать на кнопку 'Save'")
    @ru.testit.annotations.Step
    @Title("Нажать на кнопку 'Save'")
    public UserPage clickSaveBtn() {
        saveBtn.click();
        return Selenide.page(UserPage.class);
    }
}