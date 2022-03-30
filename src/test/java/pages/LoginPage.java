package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private final WebDriver driver;

    @FindBy(id = "UserLogin_username")
    private WebElement loginField;

    @FindBy(id = "UserLogin_password")
    private WebElement passField;

    @FindBy(css = "[type='submit']")
    private WebElement submitButton;

    public LoginPage(final WebDriver webDriver) throws IllegalStateException {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public final void clickSubmitButton() {
        submitButton.click();
        new DashboardPage(driver);
    }

    public final void fillLoginField(String text) {
        loginField.sendKeys(text);
    }

    public final void fillPasswordField(String text) {
        passField.sendKeys(text);
    }
}
