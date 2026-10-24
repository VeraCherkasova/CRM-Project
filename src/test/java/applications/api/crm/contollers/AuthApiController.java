package applications.api.crm.contollers;

import applications.api.crm.enums.ApiEndPoints;
import applications.api.crm.enums.StatusCode;
import applications.api.crm.pojo.request.ApiUserPost;
import applications.api.crm.specification.ApiSpec;
import assertions.AssertableResponse;
import io.qameta.allure.Step;
import ru.testit.annotations.Title;
import utils.CreateRandomData;

import java.util.List;

import static applications.api.crm.specification.ApiSpec.authAdmin;
import static io.restassured.RestAssured.given;

public class AuthApiController {

    @Step("Отправка запроса на авторизацию под API юзером")
    @ru.testit.annotations.Step
    @Title("Отправка запроса на авторизацию под API юзером")
        public static AssertableResponse getUser() {
        return new AssertableResponse(given()
                .spec(ApiSpec.authApi())
                .get(ApiEndPoints.AUTH_CRM.getPath())
                .then());
    }

    @Step("Отправка запроса на создание нового API юзера")
    @ru.testit.annotations.Step
    @Title("Отправка запроса на создание нового API юзера")
    public static String createApiUserAndGetKey() {
        ApiUserPost apiUser = CreateRandomData.getRandomApiUser();
        String roleId = getRoleIdByName("Admin");
        apiUser.setRolesIds(List.of(roleId));
        return given()
                .spec(authAdmin())
                .body(apiUser)
                .when()
                .post(ApiEndPoints.USERS_CRM.getPath())
                .then()
                .statusCode(StatusCode.OK.getCode())
                .extract().path("apiKey");
    }

    @Step("Создание роли для API юзера")
    @ru.testit.annotations.Step
    @Title("Создание роли для API юзера")
    private static String getRoleIdByName(String roleName) {
        String id = given()
                .spec(authAdmin())
                .queryParam("where[0][type]", "equals")
                .queryParam("where[0][attribute]", "name")
                .queryParam("where[0][value]", roleName)
                .when()
                .get(ApiEndPoints.ROLE_CRM.getPath())
                .then()
                .statusCode(StatusCode.OK.getCode())
                .extract().path("list[0].id");

        if (id == null) {
            throw new IllegalStateException("Роль '" + roleName + "' не найдена");
        }
        return id;
    }
}
