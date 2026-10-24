package applications.fakestore.controllers;

import applications.api.enums.ApiEndPoints;
import applications.fakestore.pojo.request.ProductPost;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.testit.annotations.Title;

public class ProductController {

    @Step("Отправка запроса на получение всех продуктов")
    @ru.testit.annotations.Step
    @Title("Отправка запроса на получение всех продуктов")
    public static ValidatableResponse getAllProduct() {
        return RestAssured.given()
                .get(ApiEndPoints.PRODUCTS.getPath())
                .then();
    }

    @Step("Отправка запроса на создание нового продукта")
    @ru.testit.annotations.Step
    @Title("Отправка запроса на создание нового продукта")
    public static ValidatableResponse postCreateProduct(ProductPost newProduct) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(newProduct)
                .post(ApiEndPoints.PRODUCTS.getPath())
                .then();
    }
}
