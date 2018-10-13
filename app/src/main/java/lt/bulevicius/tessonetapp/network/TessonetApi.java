package lt.bulevicius.tessonetapp.network;

import io.reactivex.Observable;
import lt.bulevicius.tessonetapp.network.entities.data.Country;
import lt.bulevicius.tessonetapp.network.entities.auth.TokenRequest;
import lt.bulevicius.tessonetapp.network.entities.auth.TokenResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

import static lt.bulevicius.tessonetapp.app.Constants.AUTHORISATION;

/**
 * The interface Tessonet api.
 */
public interface TessonetApi {

    /**
     * Login observable.
     *
     * @param tokenRequest the token request
     * @return the observable
     */
    @POST("v1/tokens")
    Observable<TokenResponse> login(@Body TokenRequest tokenRequest);

    /**
     * Gets countries.
     *
     * @param token the token
     * @return the countries
     */
    @GET("v1/servers")
    Observable<Country> getCountries(@Header(AUTHORISATION) String token);

}
