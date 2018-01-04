package com.azzdorfrobotics.android.testio.network;

import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

@Singleton public class TesonetHeaderInterceptor implements Interceptor {

    @Inject public TesonetHeaderInterceptor() {
    }

    @Override public Response intercept(Chain chain) throws IOException {
        Request.Builder headersBuilder = chain.request().newBuilder();
        headersBuilder.addHeader("Content-Type", "application/json");
        return chain.proceed(headersBuilder.build());
    }
}
