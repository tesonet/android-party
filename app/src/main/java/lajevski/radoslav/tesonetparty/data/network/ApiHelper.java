package lajevski.radoslav.tesonetparty.data.network;


import java.util.List;

import io.reactivex.Observable;
import lajevski.radoslav.tesonetparty.data.network.model.LoginResponse;
import lajevski.radoslav.tesonetparty.data.network.model.Server;
import lajevski.radoslav.tesonetparty.data.network.model.User;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Radoslav on 1/16/2018.
 */

public interface ApiHelper {

    @POST("v1/tokens")
    Observable<LoginResponse> getToken(@Body User user);

    @GET("v1/servers")
    Observable<List<Server>> getServers(@Header("Authorization") String token);
}
