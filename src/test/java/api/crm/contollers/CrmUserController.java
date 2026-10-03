package api.crm.contollers;

import api.crm.enums.ApiEndPoints;
import api.crm.pojo.request.UserCrmPost;
import api.crm.specification.ApiSpec;
import assertions.AssertableResponse;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CrmUserController {

    @Step("Отправка запроса на получение всех юзеров")
    public static AssertableResponse getAllUsers() {
        return new AssertableResponse(given()
                .spec(ApiSpec.authAdmin())
                .get(ApiEndPoints.USERS_CRM.getPath())
                .then());
    }

    @Step("Отправка запроса на создание новых юзеров")
      public static AssertableResponse postNewUsers(UserCrmPost newUser) {
        return new AssertableResponse(given()
                .spec(ApiSpec.authAdmin())
                .body(newUser)
                .post(ApiEndPoints.USERS_CRM.getPath())
                .then());
    }
}

