package com.example.testio;

import android.app.Application;
import android.support.annotation.NonNull;
import com.example.testio.injection.AppComponent;
import com.example.testio.injection.AppModule;
import com.example.testio.injection.DaggerAppComponent;

public final class App extends Application {
  private AppComponent mAppComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
  }

  @NonNull
  public AppComponent getAppComponent() {
    return mAppComponent;
  }
}