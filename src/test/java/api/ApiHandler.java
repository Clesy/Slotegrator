package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import config.Configs;
import domain.PlayerRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DataGenerator;
import utils.EncryptMessage;

import static io.restassured.RestAssured.given;

public class ApiHandler {
    private static final Logger D = LogManager.getLogger(ApiHandler.class);
    private static final RequestSpecification requestSpecification;

    static {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(Configs.BASE_URL)
                .setBasePath(Endpoints.API_URN.getEndpoint())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    public ApiHandler() {}

    public static Response getPlayerToken(PlayerRequest player) {
        JsonObject requestParam = new JsonObject();
        String auth = Configs.USERNAME + ":" + Configs.PASSWORD;

        if (player == null) {
            requestParam.addProperty("grant_type", "client_credentials");
            requestParam.addProperty("scope", "guest:default");
            D.debug("Player properties grant type: {}, scope: {}",
                    requestParam.get("grant_type"), requestParam.get("scope"));
        } else {
            requestParam.addProperty("grant_type", "password");
            requestParam.addProperty("username", player.username());
            requestParam.addProperty("password", player.passwordChange());
            D.debug("Player properties grant type: {}, username: {}, password: {}",
                    requestParam.get("grant_type"), requestParam.get("username"), requestParam.get("password"));
        }
        D.debug("User authorization params: {}", auth);
        Response authorization = given(requestSpecification)
                .header("Authorization", "Basic " + EncryptMessage.encode(auth))
                .body(requestParam)
                .when()
                .post(Endpoints.AUTHORIZATION_URN.getEndpoint());

        D.debug("JSON: {}", authorization.jsonPath().prettyPrint());
        return authorization;
    }

    public static Response getPlayerToken() {
        return getPlayerToken(null);
    }

    public static Response createNewPlayer(PlayerRequest player, String token) {
        D.debug("Create new player params - player: {}, token: {}", player, token);

        Response post = given(requestSpecification)
                .auth()
                .oauth2(token)
                .body(new Gson().toJson(player))
                .when()
                .post(Endpoints.PLAYERS_URN.getEndpoint());

        D.debug("JSON:  " + post.jsonPath().prettyPrint());
        return post;
    }

    public static Response getPlayerInfo(String token, int id) {
        D.debug("Get player info - token: {}, id: {}", token, id);
        return given(requestSpecification).auth().oauth2(token).get(Endpoints.PLAYERS_URN.getEndpoint() + "/" + id);
    }

    public static PlayerRequest getNewPlayer() {
        String password = DataGenerator.generateString(10);

        PlayerRequest playerRequest = new PlayerRequest(
                DataGenerator.generateString(10),
                EncryptMessage.encode(password),
                EncryptMessage.encode(password),
                DataGenerator.email(10),
                DataGenerator.userNameOrSurname(4),
                DataGenerator.userNameOrSurname(6),
                null);

        D.debug("Get new player: {}", playerRequest);
        return playerRequest;
    }
}
