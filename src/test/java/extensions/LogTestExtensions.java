package extensions;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@Slf4j
public class LogTestExtensions implements BeforeEachCallback, AfterEachCallback {

    private static final String SEPARATOR = "=".repeat(80);

    @Override
    public void beforeEach(ExtensionContext context) {
        log.info("\n{}\n>>> START TEST: {}\n{}", SEPARATOR, testName(context), SEPARATOR);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        log.info("\n{}\n<<< END TEST: {}\n{}", SEPARATOR, testName(context), SEPARATOR);
    }

    private String testName(ExtensionContext context) {
        return context.getDisplayName();
    }
}