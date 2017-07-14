package app.androidparty.Services;

import android.databinding.ObservableArrayList;

import com.google.gson.JsonObject;

import app.androidparty.Constants.Configs;
import app.androidparty.Models.Server;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Thoughtful on 2017-07-13.
 */

public interface ApiService {

    @GET("servers")
    Call<ObservableArrayList<Server>> getServers(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("tokens")
    Call<JsonObject> getToken(@Field("username") String username, @Field("password") String password);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Configs.getApiUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
