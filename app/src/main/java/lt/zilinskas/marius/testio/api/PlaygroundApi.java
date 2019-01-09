package lt.zilinskas.marius.testio.api;

import java.util.List;

import io.reactivex.Observable;
import lt.zilinskas.marius.testio.api.entities.Server;
import lt.zilinskas.marius.testio.api.entities.Token;
import lt.zilinskas.marius.testio.api.entities.UserInfo;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PlaygroundApi {

    @Headers("Content-Type: application/json")
    @POST("/v1/tokens")
    Observable<Token> authorize(@Body UserInfo userInfo);

    @GET("/v1/servers")
    Observable<List<Server>> getServers(@Header("Authorization") String authorization);

}
