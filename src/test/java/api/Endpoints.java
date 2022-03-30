package api;

public enum Endpoints {
    API_URN("/v2/"),
    AUTHORIZATION_URN("oauth2/token"),
    PLAYERS_URN("players");

    private final String endpoint;

    Endpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
