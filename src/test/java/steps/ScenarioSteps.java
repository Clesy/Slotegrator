package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PlayerTablePage;

public class ScenarioSteps {
    private final WebDriver webDriver = BaseSteps.webDriver;

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private PlayerTablePage playerManagementPage;

    @Given("I'm open {string} site")
    public void openSite(String site) {
        webDriver.get(site);
        loginPage = new LoginPage(webDriver);
    }

    @When("User input login {string} on field")
    public void userInputLoginOnField(String login) {
        loginPage.fillLoginField(login);
    }

    @And("User input password {string} on field")
    public void userInputPasswordOnField(String password) {
        loginPage.fillPasswordField(password);
    }

    @And("User click button 'Sign in'")
    public void userClickButtonSignIn() {
        loginPage.clickSubmitButton();
        dashboardPage = new DashboardPage(webDriver);
    }

    @And("User click button 'Users'")
    public void userClickButtonUsers() {
        dashboardPage.clickUsersButton();
    }

    @And("User click links 'Player'")
    public void userClickLinksPlayer() {
        dashboardPage.clickPlayersLink();
        playerManagementPage = new PlayerTablePage(webDriver);
    }

    @And("User click links 'ExternalId'")
    public void userClickLinksExternalId() {
        playerManagementPage.clickSortByExternalId();
    }

    @Then("Dashboard is loaded")
    public void dashboardIsLoaded() {
        dashboardPage.checkPageLoad();
    }

    @Then("Users table is loaded")
    public void usersTableIsLoaded() {
        playerManagementPage.checkPageLoad();
    }

    @Then("Table sorted by 'ExternalId' row")
    public void tableSortedByExternalIdRow() {
        playerManagementPage.checkExternalIdOrder();
    }
}
