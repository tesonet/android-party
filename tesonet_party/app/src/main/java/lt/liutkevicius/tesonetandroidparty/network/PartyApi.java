package lt.liutkevicius.tesonetandroidparty.network;

import io.reactivex.Observable;
import lt.liutkevicius.tesonetandroidparty.network.model.Token;
import lt.liutkevicius.tesonetandroidparty.network.request.LoginRequest;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface PartyApi {

    @POST("v1/tokens")
    Observable<Token> login(@Body LoginRequest loginRequest);
}
