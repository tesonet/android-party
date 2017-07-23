package com.example.testio.api;

import com.example.testio.models.Server;
import com.example.testio.models.Token;
import com.example.testio.models.User;
import io.reactivex.Single;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by mantas on 7/22/17.
 */

public interface TestioApi {

  @POST("tokens")
  Single<Token> postUserGetToken(@Header("Content-Type") String content_type, @Body User user);

  @GET("servers")
  Single<List<Server>> getServers(@Header("Authorization") String authHeader);
}
