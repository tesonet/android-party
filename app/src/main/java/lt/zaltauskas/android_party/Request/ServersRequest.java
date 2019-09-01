package lt.zaltauskas.android_party.Request;

import java.util.List;

import lt.zaltauskas.android_party.Model.Server;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ServersRequest {

    @GET("v1/servers")
    Call<List<Server>> getServersList(@Header("Authorization") String auth);

}
