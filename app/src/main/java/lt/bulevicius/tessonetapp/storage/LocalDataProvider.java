package lt.bulevicius.tessonetapp.storage;

import java.util.List;

import lt.bulevicius.tessonetapp.network.entities.data.Country;

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

    void setCountries(List<Country> countries);
}
