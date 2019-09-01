package lt.zaltauskas.android_party.Request;


import lt.zaltauskas.android_party.Model.Token;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AuthRequest {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("v1/tokens")
    Call<Token> requestTokenForm(
            @Field("username") String username,
            @Field("password") String password);
}