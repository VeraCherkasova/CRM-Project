package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private final SelenideElement treeDotsBtn = $("#nav-menu-dropdown");
    private final SelenideElement adminBtn = $x("//ul[@class='dropdown-menu']//a[contains(@href, 'User/')]");

    public MainPage() {
        treeDotsBtn.should(Condition.visible.because("Страница не открылась"));
    }

    @Step("Нажать на кнопку три точки")
    public MainPage clickTreeDotsBtn() {
        treeDotsBtn.click();
        return this;
    }

    @Step("Нажать на кнопку Admin")
    public UserViewPage clickAdminBtn() {
        adminBtn.click();
        return Selenide.page(UserViewPage.class);
    }
}
