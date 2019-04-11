package lt.liutkevicius.tesonetandroidparty.network;

import com.google.gson.JsonElement;
import io.reactivex.Observable;
import lt.liutkevicius.tesonetandroidparty.network.model.Server;
import lt.liutkevicius.tesonetandroidparty.network.model.Token;
import lt.liutkevicius.tesonetandroidparty.network.request.LoginRequest;
import lt.liutkevicius.tesonetandroidparty.utils.Constants;
import retrofit2.http.*;

import java.util.List;


public interface PartyApi {

    @POST("v1/tokens")
    Observable<Token> login(@Body LoginRequest loginRequest);

    @GET("v1/servers")
    Observable<JsonElement/*List<Server>*/> getServers(@Header(Constants.AUTHORIZATION) String token);
}
