package pages;

import com.google.common.collect.Ordering;
import driver.Waiters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTablePage {
    private final WebDriver driver;

    @FindBy(className = "panel-heading")
    private WebElement playerManagementPanel;

    @FindBy(css = "[id$='grid_c2'] a")
    private WebElement sortByExternalIdLink;

    @FindBy(css = "tbody tr td:nth-child(3)")
    private List<WebElement> externalIds;

    @FindBy(className = "grid-view")
    private WebElement loader;

    public PlayerTablePage(final WebDriver webDriver) throws IllegalStateException {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
        Waiters.waitVisibleElement(driver, playerManagementPanel);
    }

    public void clickSortByExternalId() {
        sortByExternalIdLink.click();
        Waiters.waitElementAttribute(driver, loader, "class", "grid-view");
    }

    public void checkExternalIdOrder() {
        List<String> list = externalIds.stream().map(WebElement::getText).collect(Collectors.toList());
        assertThat(Ordering.natural().isOrdered(list)).isTrue();
    }

    public void checkPageLoad() {
        assertThat(playerManagementPanel.isDisplayed()).isTrue();
    }
}
