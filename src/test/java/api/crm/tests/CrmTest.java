package api.crm.tests;

import annotations.Run;
import api.crm.contollers.AuthApiController;
import api.crm.contollers.CrmUserController;
import api.crm.db.CrmDbClient;
import api.crm.dto.ApiUser;
import api.crm.pojo.request.UserCrmPost;
import org.junitpioneer.jupiter.RetryingTest;
import utils.CreateRandomData;
import api.crm.enums.StatusCode;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CrmTest extends BaseCrmTest {

    @Run
    @Test
    @RetryingTest(maxAttempts = 3, suspendForMs = 500, name = "{displayName} | {index}")
    @DisplayName("Проверка конкретного юзера в списке юзеров")
    public void testGetAllCrmUser() {
        CrmUserController.getAllUsers().log().all()
                .statusCode(StatusCode.OK.getCode())
                // первый элемент — Admin
                .body("list[0].userName", Matchers.equalTo("admin"))
                .body("list[0].name", Matchers.equalTo("Admin"))
                .body("list[0].type", Matchers.equalTo("admin"))
                .body("list[0].isActive", Matchers.equalTo(true));
    }

    @Test
    @DisplayName("Логин под юзером с API key и проверка ответа")
    public void userProfileTest() {
        AuthApiController.getUser()
                .statusCode(StatusCode.OK.getCode());

        ApiUser dbUser = CrmDbClient.getUserFromDb();
        assertThat(dbUser).as("API-пользователь в БД").isNotNull();

        AuthApiController.getUser()
                .statusCode(StatusCode.OK.getCode())
                .body("user.userName", Matchers.equalTo(dbUser.getUserName()))
                .body("user.firstName", Matchers.equalTo(dbUser.getFirstName()))
                .body("user.lastName", Matchers.equalTo(dbUser.getLastName()))
                .body("user.type", Matchers.equalTo(dbUser.getType()))
                .body("user.isActive", Matchers.equalTo(dbUser.getIsActive()));
    }

    @Test
    @DisplayName("Создание нового пользователя")
    public void testPostCreateNewUser() {
        UserCrmPost newUser = CreateRandomData.getRandomCrmUser();
        CrmUserController.postNewUsers(newUser)
                .statusCode(StatusCode.OK.getCode())
                .body("salutationName", Matchers.equalTo(newUser.getSalutationName()))
                .body("firstName", Matchers.equalTo(newUser.getFirstName()))
                .body("lastName", Matchers.equalTo(newUser.getLastName()));
    }


}
