package applications.api.crm.db;

import applications.api.crm.dto.ApiUser;
import config.ConfigProvider;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import ru.testit.annotations.Title;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static applications.api.crm.contollers.AuthApiController.createApiUserAndGetKey;

@Slf4j
public class CrmDbClient {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(ConfigProvider.TEST_CONFIGURATION_DB.dbUrl(),
                ConfigProvider.TEST_CONFIGURATION_DB.dbUser(),
                ConfigProvider.TEST_CONFIGURATION_DB.dbPassword());
    }

    @Step("Получение API юзера из БД")
    @ru.testit.annotations.Step
    @Title("Получение API юзера из БД")
    public static ApiUser getUserFromDb() {
        String sql = """
                SELECT user_name, api_key, first_name, last_name, type, is_active FROM `user`
                            WHERE type = 'api'
                         AND deleted = 0
                       AND is_active = 1
                          AND api_key IS NOT NULL
                         AND api_key <> ''
                         LIMIT 1""";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return new ApiUser(
                        rs.getString("user_name"),
                        rs.getString("api_key"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("type"),
                        rs.getBoolean("is_active")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось получить API-пользователя из БД", e);
        }
        return null;
    }

    public static ApiUser getApiKeyFromDb() {
        ApiUser user = getUserFromDb();
        if (user != null && user.getApiKey() != null && !user.getApiKey().isBlank()) {
            return user;
        }
        createApiUserAndGetKey();
        ApiUser created = getUserFromDb();
        if (created == null) {
            throw new IllegalStateException(
                    "createApiUserAndGetKey() отработал, но пользователь в БД не найден");
        }
        return created;
    }
}
