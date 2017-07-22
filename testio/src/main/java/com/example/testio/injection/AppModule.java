package com.example.testio.injection;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.example.testio.App;
import com.example.testio.api.TestioApi;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public final class AppModule {

  //// TODO: 7/22/17 hide me
  final String BASE_URL= "http://playground.tesonet.lt/v1/";

  @NonNull
  private final App mApp;

  public AppModule(@NonNull App app) {
    mApp = app;
  }

  @Provides
  public Context provideAppContext() {
    return mApp;
  }

  @Provides
  public App provideApp() {
    return mApp;
  }

  @Provides
  public GsonConverterFactory providesGsonConverterFactory() {
    return GsonConverterFactory.create();
  }

  @Provides
  public RxJava2CallAdapterFactory providesRxJava2CallAdapterFactory() {
    return RxJava2CallAdapterFactory.create();
  }

  @Provides
  public OkHttpClient providesOkHttpClient() {
    return new OkHttpClient.Builder().build();
  }

  @Provides
  public TestioApi provideTestioApi(
      GsonConverterFactory gsonConverterFactory,
      RxJava2CallAdapterFactory rxJava2CallAdapterFactory, OkHttpClient client) {

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient client1 = new OkHttpClient.Builder().addInterceptor(interceptor).build();

    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(rxJava2CallAdapterFactory)
        .client(client1)
        .build();

    return retrofit.create(TestioApi.class);
  }

  @Provides
  public SharedPreferences providePrefs(App application) {

    return application.getSharedPreferences("some_prefs_name", 0);
  }


}
