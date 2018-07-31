package lt.tesonet.network;

import java.util.List;

import io.reactivex.Single;
import lt.tesonet.model.Server;
import lt.tesonet.model.User;
import lt.tesonet.model.Token;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @POST("tokens")
    @Headers("Content-Type: application/json")
    Single<Response<Token>> logIn(@Body User user);

    @GET("servers")
    Single<Response<List<Server>>> getServers(@Header("Authorization") String header);
}
