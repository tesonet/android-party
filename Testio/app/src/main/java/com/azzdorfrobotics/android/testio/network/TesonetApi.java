package com.azzdorfrobotics.android.testio.network;

import com.azzdorfrobotics.android.testio.network.model.request.LoginRequest;
import com.azzdorfrobotics.android.testio.network.model.response.LoginResponse;
import com.azzdorfrobotics.android.testio.network.model.response.Server;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

public interface TesonetApi {

    String API_VERSION_1 = "/v1";

    @POST(API_VERSION_1 + "/tokens") Observable<LoginResponse>  login(@Body LoginRequest request);

    @GET(API_VERSION_1 + "/servers") Observable<List<Server>> servers(@Header("Authorization") String token);
}
