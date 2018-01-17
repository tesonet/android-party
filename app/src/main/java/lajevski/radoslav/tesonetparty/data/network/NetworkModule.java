package lajevski.radoslav.tesonetparty.data.network;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lajevski.radoslav.tesonetparty.data.DataManager;
import lajevski.radoslav.tesonetparty.utils.AppNetwork;
import lajevski.radoslav.tesonetparty.utils.AppString;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Radoslav on 1/16/2018.
 */

@Module
public class NetworkModule {

    private static final String AUTHORIZATION_TOKEN_PREFIX = "Bearer ";

    private String mSessionToken;

    private Context mContext;

    public NetworkModule(Context context) {
        this.mContext = context;
    }

    public void setSessionToken(String sessionToken) {
        this.mSessionToken = sessionToken;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl("http://playground.tesonet.lt/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Interceptor interceptor) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor);

        return httpClientBuilder.build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor(){
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    Interceptor provideInterceptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                request = request.newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build();

                if (!AppNetwork.isNetworkConnectionPresent(mContext)){throw new IOException("connection_error");}

                Response response = chain.proceed(request);

                switch (response.code()){
                    case HttpURLConnection.HTTP_OK:
                        break;
                    case HttpURLConnection.HTTP_UNAUTHORIZED:
                        throw new IOException("Unauthorized");
                    case HttpURLConnection.HTTP_INTERNAL_ERROR:
                        throw new IOException("Server Error");
                    default:
                        throw new IOException("Error");
                }

                return response;
            }
        };
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    @Singleton
    ApiHelper provideAppApi(Retrofit retrofit) {
        return retrofit.create(ApiHelper.class);
    }
}
