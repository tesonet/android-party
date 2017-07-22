package com.example.testio.api;

import com.example.testio.models.Token;
import com.example.testio.models.User;
import com.example.testio.models.Server;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by mantas on 7/22/17.
 */

public interface TestioApi {

  //[{"key":"Content-type","value":"application/json; charset=utf-8"}]
  //{"username": "tesonet", "password": "partyanimal"}

  @POST("tokens")
  Observable<Token> postUserGetToken(@Header("Content-Type") String content_type, @Body User user);


  // authHeader = Bearer token
  @GET("servers")
  Observable<List<Server>> getServers(@Header("Authorization") String authHeader);
}
