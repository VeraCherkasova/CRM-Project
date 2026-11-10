package applications.api.contollers;

import applications.api.enums.ApiEndPoints;
import applications.api.pojo.UserCrmPost;
import applications.api.specification.ApiSpec;
import assertions.AssertableResponse;
import io.qameta.allure.Step;
import ru.testit.annotations.Title;

import static io.restassured.RestAssured.given;

public class CrmUserController {

    @Step("Отправка запроса на получение всех юзеров")
    @ru.testit.annotations.Step
    @Title("Отправка запроса на получение всех юзеров")
    public static AssertableResponse getAllUsers() {
        return new AssertableResponse(given()
                .spec(ApiSpec.authAdmin())
                .get(ApiEndPoints.USERS_CRM.getPath())
                .then());
    }

    @Step("Отправка запроса на создание новых юзеров")
    @ru.testit.annotations.Step
    @Title("Отправка запроса на создание новых юзеров")
      public static AssertableResponse postNewUsers(UserCrmPost newUser) {
        return new AssertableResponse(given()
                .spec(ApiSpec.authAdmin())
                .body(newUser)
                .post(ApiEndPoints.USERS_CRM.getPath())
                .then());
    }
}

