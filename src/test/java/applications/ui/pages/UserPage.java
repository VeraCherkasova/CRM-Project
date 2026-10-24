package applications.ui.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import ru.testit.annotations.Title;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class UserPage {
    private final SelenideElement createUserBtn = $("[data-name='create']");
    private final SelenideElement treeDotsBtn = $("#nav-menu-dropdown.dropdown-toggle[role='button']");
    private final SelenideElement logOutBtn = $("[data-name='logout']");
    private final ElementsCollection userItems = $$(".list-row");

    @Step("Нажать на кнопку Сreate User")
    @ru.testit.annotations.Step
    @Title("Нажать на кнопку Сreate User")
    public UserPage clickCreateUserBtn() {
        createUserBtn.click();
        return this;
    }

    @Step("Нажать на кнопку три точки")
    @ru.testit.annotations.Step
    @Title("Нажать на кнопку три точки")
    public UserPage clickTreeDotsBtn() {
        treeDotsBtn.click();
        return this;
    }

    @Step("Нажать на кнопку Log Out")
    @ru.testit.annotations.Step
    @Title("Нажать на кнопку Log Out")
    public UserPage clickLogOutBtn() {
        logOutBtn.click();
        return this;
    }

    @Step("Найти пользователя {userForSearch} в списке пользователей")
    @ru.testit.annotations.Step
    @Title("Найти пользователя {userForSearch} в списке пользователей")
    public UserPage findUser(String userForSearch) {
        userItems.findBy(Condition.text(userForSearch)).shouldBe(Condition.visible);
        return this;
    }
}
