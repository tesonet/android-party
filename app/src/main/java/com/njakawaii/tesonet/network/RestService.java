package com.njakawaii.tesonet.network;

import com.orhanobut.hawk.Hawk;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import static com.njakawaii.tesonet.Constants.BASE_URL;
import static com.njakawaii.tesonet.Constants.TOKEN;


public class RestService {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static RestApi createRestService(){

        Retrofit.Builder builder = new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL);

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                        .addInterceptor(chain -> {
                            Request request = chain.request();
                            Request.Builder newBuilder = request.newBuilder();

                            newBuilder.addHeader("Accept", "application/json");
                            newBuilder.addHeader("Accept-Language", "en");
                            newBuilder.addHeader("Content-Type", "application/json");


                            String token = Hawk.get(TOKEN, "");
                            if (token != null && !token.isEmpty()) {
                                newBuilder.addHeader("Authorization", "Bearer " + token);
                                Timber.d("Authorization: Token " + token);
                            }
                            Request newRequest = newBuilder
                                    .build();
                            Response response = null;
                            String newStringBody = "";
                            try {
                                response = chain.proceed(newRequest);
                                ResponseBody body = response.body();
                                Timber.d("HTTP " + response.code() + " URL=" + response.request().url().toString());
                                String bodyString = body.string();
                                Timber.d(bodyString);
                                if (bodyString.startsWith("[")) {
                                    newStringBody = "{\"data\":" + bodyString + "}";
                                }
                                else if (bodyString == null || bodyString.isEmpty()){
                                    newStringBody = "{}";
                                }
                                else {
                                    newStringBody = bodyString;
                                }
                                Timber.d(newStringBody);
                                final Response.Builder newResponse = response.newBuilder()
                                        .body(ResponseBody.create(JSON, newStringBody));
                                response = newResponse.build();

                            } catch (IOException e) {
                                e.getLocalizedMessage();
                            }
                            return response;
                        })
            .build();
        builder.client(client);
        return builder.build().create(RestApi.class);
    }

}
