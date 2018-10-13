package lt.bulevicius.tessonetapp.storage;

/**
 * The interface Local data provider.
 */
public interface LocalDataProvider {

    /**
     * Sets token.
     *
     * @param token the token
     */
    void setToken(String token);

    /**
     * Gets token.
     *
     * @return the token
     */
    String getToken();
}
