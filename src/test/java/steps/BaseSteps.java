package steps;

import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class BaseSteps {
    public static WebDriver webDriver;

    @Before
    public void setDriver() {
        webDriver = DriverFactory.createDriver();
    }

    @After
    public void stopDriver() {
        webDriver.quit();
        webDriver = null;
    }
}