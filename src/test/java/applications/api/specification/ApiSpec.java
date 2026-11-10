package applications.api.specification;

import config.ConfigProvider;
import applications.api.db.CrmDbClient;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiSpec {

    public static RequestSpecification authApi() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigProvider.TEST_CONFIGURATION.crmApiUrl())
                .addHeader("X-Api-Key", CrmDbClient.getApiKeyFromDb().getApiKey())
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification authAdmin() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigProvider.TEST_CONFIGURATION.crmApiUrl())
                .setContentType(ContentType.JSON)
                .setAuth(RestAssured.preemptive().basic(
                        ConfigProvider.TEST_CONFIGURATION.authLogin(),
                        ConfigProvider.TEST_CONFIGURATION.authPassword()))
                .build();
    }
}
