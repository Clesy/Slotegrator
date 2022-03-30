package tests;

import api.ApiHandler;
import domain.PlayerRequest;
import domain.PlayerResponse;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.DataGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class ApiTests {
    private PlayerRequest playerRequest;

    @BeforeMethod(groups = {"api"})
    public void setUp() {
        playerRequest = ApiHandler.getNewPlayer();
    }

    @Test(groups = {"api"})
    public final void guestTokenCanBeRetrieved() {
        ApiHandler
                .getPlayerToken()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("access_token", notNullValue());
    }

    @Test(groups = {"api"})
    public final void playerRegistrationCanBeDone() {
        String token = ApiHandler
                .getPlayerToken()
                .jsonPath()
                .get("access_token");

        var response = ApiHandler
                .createNewPlayer(playerRequest, token)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(PlayerResponse.class);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(playerRequest.username())
                    .as("Current username is not correct")
                    .isEqualTo(response.getUsername());

            softAssertions.assertThat(playerRequest.email())
                    .as("Current email is not correct")
                    .isEqualTo(response.getEmail());

            softAssertions.assertThat(playerRequest.surname())
                    .as("Current surname is not correct")
                    .isEqualTo(response.getSurname());

            softAssertions.assertThat(playerRequest.name())
                    .as("Current name is not correct")
                    .isEqualTo(response.getName());
        });
    }

    @Test(groups = {"api"})
    public void userTokenCanBeRetrieved() {
        String token = ApiHandler
                .getPlayerToken()
                .jsonPath()
                .get("access_token");

        ApiHandler.createNewPlayer(playerRequest, token);

        ApiHandler
                .getPlayerToken(playerRequest)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("access_token", notNullValue());
    }

    @Test(groups = {"api"})
    public void playerInfoWithIncorrectJsonParamsCanBeRetrieved() {
        String guestToken = ApiHandler
                .getPlayerToken()
                .jsonPath()
                .get("access_token");

        ApiHandler.createNewPlayer(playerRequest, guestToken).as(PlayerResponse.class);

        String userToken = ApiHandler
                .getPlayerToken(playerRequest)
                .jsonPath()
                .get("access_token");

        var response = ApiHandler
                .getPlayerInfo(userToken, DataGenerator.randomInteger())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(PlayerResponse.class);

        assertThat(response).isNotNull();
    }

    @Test(groups = {"api"})
    public void playerInfoWithCorrectJsonParamsCanBeRetrieved() {
        String guestToken = ApiHandler
                .getPlayerToken()
                .jsonPath()
                .get("access_token");

        PlayerResponse expectedInfo = ApiHandler
                .createNewPlayer(playerRequest, guestToken)
                .as(PlayerResponse.class);

        String userToken = ApiHandler
                .getPlayerToken(playerRequest)
                .jsonPath()
                .get("access_token");

        var response = ApiHandler
                .getPlayerInfo(userToken, expectedInfo.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(PlayerResponse.class);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(playerRequest.username())
                    .as("Current username is not correct")
                    .isEqualTo(response.getUsername());

            softAssertions.assertThat(playerRequest.email())
                    .as("Current email is not correct")
                    .isEqualTo(response.getEmail());

            softAssertions.assertThat(playerRequest.surname())
                    .as("Current surname is not correct")
                    .isEqualTo(response.getSurname());

            softAssertions.assertThat(playerRequest.name())
                    .as("Current name is not correct")
                    .isEqualTo(response.getName());
        });
    }
}
