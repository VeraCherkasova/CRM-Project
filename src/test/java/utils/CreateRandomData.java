package utils;

import applications.api.pojo.ApiUserPost;
import applications.api.pojo.UserCrmPost;
import ru.testit.annotations.Step;
import ru.testit.annotations.Title;

import java.util.List;

import static utils.RandomUtils.FAKER;

public class CreateRandomData {

    @Step
    @Title("Создание нового рандомного пользователя")
    public static UserCrmPost getRandomCrmUser() {
        return UserCrmPost.builder()
                .userName(FAKER.internet().username())
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .salutationName("Mr.")
                .type("regular")
                .isActive(true)
                .build();
    }

    @Step
    @Title("Создание нового рандомного пользователя")
    public static ApiUserPost getRandomApiUser() {
        return ApiUserPost.builder()
                .userName(FAKER.internet().username())
                .type("api")
                .authMethod("ApiKey")
                .isActive(true)
                .rolesIds(List.of())
                .build();
    }
}
