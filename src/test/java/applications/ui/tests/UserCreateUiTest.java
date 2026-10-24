package applications.ui.tests;

import annotations.Run;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import applications.ui.pages.LoginPage;
import applications.ui.pages.UserCreatePage;
import applications.ui.pages.UserPage;
import ru.testit.annotations.ExternalId;
import ru.testit.annotations.WorkItemIds;

import java.io.File;

import static com.codeborne.selenide.Selenide.open;
import static utils.RandomUtils.FAKER;

public class UserCreateUiTest extends BaseUiTest {

    private final String nameMrs = "Mrs.";
    private final String userName = FAKER.internet().username();
    private final String passwordNewUser = FAKER.internet().password();
    private final String firstName = FAKER.name().firstName();
    private final String lastName = FAKER.name().lastName();
    private final File pictureUploadDoc = new File("src/test/resources/upload/cat.jpg");

    @Run
    @ExternalId("172")
    @WorkItemIds("172")
    @Test
    @DisplayName("UI. Создание нового пользователя")
    @ru.testit.annotations.DisplayName("UI. Создание нового пользователя")
    public void testCreateUser() {
        open("", LoginPage.class)
                .setLogin(login)
                .setPassword(password)
                .clickLogIn();

        open("#User", UserPage.class)
                .clickCreateUserBtn();

        open("#User/create", UserCreatePage.class)
                .setUserName(userName.toLowerCase())
                .setName(nameMrs)
                .setFirstName(firstName)
                .setLastName(lastName)
                .uploadAvatar(pictureUploadDoc)
                .clickSubmitAvatarBtn()
                .setPassword(passwordNewUser)
                .setConfirmPassword(passwordNewUser)
                .clickSaveBtn()
                .clickTreeDotsBtn()
                .clickLogOutBtn();

        open("", LoginPage.class)
                .setLogin(userName)
                .setPassword(passwordNewUser)
                .clickLogIn()
                .clickTreeDotsBtn()
                .clickAdminBtn()
                .checkUserName(userName)
                .checkName("%s %s %s".formatted(nameMrs, firstName, lastName));

        open("#User", UserPage.class)
                .findUser(userName);
    }
}
