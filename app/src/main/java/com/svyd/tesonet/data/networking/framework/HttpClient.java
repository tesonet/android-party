package com.svyd.tesonet.data.networking.framework;

import com.svyd.tesonet.data.networking.global.ApiConstants;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpClient {

    private OkHttpClient mClient;

    private static HttpClient sInstance;

    private HttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(ApiConstants.LOGGING_LEVEL);


        mClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(ApiConstants.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(ApiConstants.READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(ApiConstants.WRITE_TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    public static HttpClient getInstance() {
        if (sInstance == null) {
            sInstance = new HttpClient();
        }
        return sInstance;
    }

    public OkHttpClient getClient() {
        return mClient;
    }

}