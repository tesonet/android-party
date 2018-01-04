package com.azzdorfrobotics.android.testio.network;

import android.content.Context;
import android.net.ConnectivityManager;
import com.azzdorfrobotics.android.testio.network.model.NetworkState;
import com.azzdorfrobotics.android.testio.preferences.Key;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

@Module public class NetworkModule {

    @Provides @Singleton Gson provideGson() {
        /**
         * Custom converters have to be added here
         */
        return new Gson();
    }

    @Provides @Singleton GsonConverterFactory provideGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides @Singleton OkHttpClient provideHttpClient(
        TesonetHeaderInterceptor tesonetHeaderInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(tesonetHeaderInterceptor);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);

        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        builder.connectTimeout(20, TimeUnit.SECONDS);

        return builder.build();
    }

    @Provides @Singleton TesonetApi provideTesonetApi(@Named(Key.API_URL) String url,
        OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder().baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(TesonetApi.class);
    }

    @Provides @Singleton ConnectivityManager provideConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides @Singleton NetworkState provideNetworkState(
        ConnectivityManager connectivityManagerCompat) {
        return new NetworkState(connectivityManagerCompat);
    }
}
