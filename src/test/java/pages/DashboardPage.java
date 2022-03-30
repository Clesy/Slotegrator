package pages;

import driver.Waiters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class DashboardPage {

    private final WebDriver driver;

    @FindBy(id = "nav")
    private WebElement navSideBar;

    @FindBy(css = "[data-target='#s-menu-users']")
    private WebElement usersButton;

    @FindBy(css = "ul[id$='-users'] a[href*='player/']")
    private WebElement playersLink;

    public DashboardPage(final WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
        Waiters.waitVisibleElement(driver, navSideBar);
    }

    public void clickUsersButton() {
        usersButton.click();
    }

    public void clickPlayersLink() {
        Waiters.waitClickableElement(driver, playersLink);
        playersLink.click();
        new PlayerTablePage(driver);
    }

    public void checkPageLoad() {
        assertThat(navSideBar.isDisplayed()).isTrue();
    }
}
