package driver;

import config.Configs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.time.Duration;

public class DriverFactory {
    private static final Logger D = LogManager.getLogger(DriverFactory.class);

    private static WebDriver getDriver(String browserName) {
        return switch (browserName) {
            case "chrome", "opera" -> new ChromeDriver();
            case "firefox" -> new FirefoxDriver();
            case "edge" -> new EdgeDriver();
            default -> throw new IllegalStateException("Chosen browser not supported");
        };
    }

    public static WebDriver createDriver() {
        String browserName = Configs.BASE_BROWSER;
        int pageLoadTimeout = Configs.PAGE_LOAD_TIMEOUT;
        trySetDriverPath(browserName);
        try {
            WebDriver driver = getDriver(browserName);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
            D.debug("Driver setting: {}", driver);
            return driver;
        } catch (IllegalStateException e) {
            throw new IllegalStateException();
        }
    }

    private static void trySetDriverPath(final String browserName) {
        String driverPath = new File(Configs.BASE_DRIVER_PATH).getAbsolutePath();
        if (!driverPath.isBlank()) {
            D.debug("System set property " + System.setProperty("webdriver." + browserName + ".driver", driverPath));
        }
    }
}
