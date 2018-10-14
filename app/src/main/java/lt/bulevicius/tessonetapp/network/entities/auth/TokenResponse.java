package lt.bulevicius.tessonetapp.network.entities.auth;

/**
 * The type Token response.
 */
public final class TokenResponse {

    private String token;

    /**
     * Instantiates a new Token response.
     *
     * @param token the token
     */
    public TokenResponse(String token) {
        this.token = token;
    }

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets token.
     *
     * @param token the token
     */
    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public String toString() {
        return "TokenResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
