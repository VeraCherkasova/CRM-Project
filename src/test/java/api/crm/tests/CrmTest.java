package api.crm.tests;

import annotations.Run;
import api.crm.contollers.AuthApiController;
import api.crm.contollers.CrmUserController;
import api.crm.db.CrmDbClient;
import api.crm.dto.ApiUser;
import api.crm.enums.StatusCode;
import api.crm.pojo.request.UserCrmPost;
import assertions.Conditions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.RetryingTest;
import ru.testit.annotations.ExternalId;
import ru.testit.annotations.Step;
import ru.testit.annotations.WorkItemIds;
import utils.CreateRandomData;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CrmTest extends BaseCrmTest {

    @Run
    @ExternalId("25")
    @WorkItemIds("25")
    @RetryingTest(maxAttempts = 3, suspendForMs = 500, name = "{displayName} | {index}")
    @DisplayName("Проверка конкретного юзера в списке юзеров")
    @Step

    public void testGetAllCrmUser() {
        CrmUserController.getAllUsers()
                .should(Conditions.hasStatusCode(StatusCode.OK.getCode()))
                .should(Conditions.hasBody("list[0].userName", Matchers.equalTo("admin")))
                .should(Conditions.hasBody("list[0].name", Matchers.equalTo("Admin")))
                .should(Conditions.hasBody("list[0].type", Matchers.equalTo("admin")))
                .should(Conditions.hasBody("list[0].isActive",Matchers.equalTo(true)));
    }

    @Run
    @ExternalId("64")
    @WorkItemIds("64")
    @Test
    @DisplayName("Логин под юзером с API key и проверка ответа")
    @Step
    public void userProfileTest() {
        AuthApiController.getUser()
                .should(Conditions.hasStatusCode(StatusCode.OK.getCode()));

        ApiUser dbUser = CrmDbClient.getUserFromDb();
        assertThat(dbUser).as("API-пользователь в БД").isNotNull();

        AuthApiController.getUser()
                .should(Conditions.hasStatusCode(StatusCode.OK.getCode()))
                .should(Conditions.hasBody("user.userName", Matchers.equalTo(dbUser.getUserName())))
                .should(Conditions.hasBody("user.firstName", Matchers.equalTo(dbUser.getFirstName())))
                .should(Conditions.hasBody("user.lastName", Matchers.equalTo(dbUser.getLastName())))
                .should(Conditions.hasBody("user.type", Matchers.equalTo(dbUser.getType())))
                .should(Conditions.hasBody("user.isActive", Matchers.equalTo(dbUser.getIsActive())));
    }

    @Run
    @ExternalId("65")
    @WorkItemIds("65")
    @Test
    @DisplayName("Создание нового пользователя")
    @Step
    public void testPostCreateNewUser() {
       UserCrmPost newUser = CreateRandomData.getRandomCrmUser();
       CrmUserController.postNewUsers(newUser)
               .should(Conditions.hasStatusCode(StatusCode.OK.getCode()))
               .should(Conditions.hasBody("salutationName", Matchers.equalTo(newUser.getSalutationName())))
               .should(Conditions.hasBody("firstName", Matchers.equalTo(newUser.getFirstName())))
               .should(Conditions.hasBody("lastName", Matchers.equalTo(newUser.getLastName())));
    }
}
