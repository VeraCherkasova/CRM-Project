package applications.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import ru.testit.annotations.Title;

import static com.codeborne.selenide.Selenide.$;

public class UserViewPage {

    private final SelenideElement userName = $(".field[data-name='userName']");
    private final SelenideElement name = $(".field[data-name='name']");

    @Step("Проверить заполнение поля 'User Name' на соответствие ожидаемому результату: {expectedName}")
    @ru.testit.annotations.Step
    @Title("Проверить заполнение поля 'User Name' на соответствие ожидаемому результату: {expectedName}")
    public UserViewPage checkUserName(String expectedName) {
        userName.should(Condition.visible);
        String actualName = userName.getText().toLowerCase();
        Assertions.assertTrue(actualName.contains(expectedName.toLowerCase()), "Поле User Name не содержит ожидаемый текст: %s".formatted(expectedName));
        return this;
    }

    @Step("Проверить заполнение поля 'Name' на соответствие ожидаемому результату: {expectedName}")
    @ru.testit.annotations.Step
    @Title("Проверить заполнение поля 'Name' на соответствие ожидаемому результату: {expectedName}")
    public UserViewPage checkName(String expectedName) {
        name.should(Condition.visible);
        Assertions.assertTrue(name.getText().contains(expectedName), "Поле Name не содержит ожидаемый текст: " + expectedName);
        return this;
    }
}
