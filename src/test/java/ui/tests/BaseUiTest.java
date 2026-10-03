package ui.tests;

import com.codeborne.selenide.Selenide;
import config.DriverConfiguration;
import extensions.AllureExtensions;
import extensions.TestLoggingExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({AllureExtensions.class,TestLoggingExtension.class})
public class BaseUiTest {

    protected final String login = "admin";
    protected final String password = "admin";

    @BeforeAll
    public static void setup() {
        DriverConfiguration.setupDriver();
    }

    @AfterAll
    public static void close() {
        Selenide.closeWebDriver();
    }
}
