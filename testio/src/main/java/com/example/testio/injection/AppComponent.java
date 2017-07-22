package com.example.testio.injection;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.testio.App;
import com.example.testio.api.TestioApi;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {
  Context getAppContext();

  App getApp();

  TestioApi getTestioApi();

  SharedPreferences getSharedPrefs();

}