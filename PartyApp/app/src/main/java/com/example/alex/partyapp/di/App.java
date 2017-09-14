package com.example.alex.partyapp.di;

import android.app.Application;
import android.content.Context;

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        this.appComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private ApplicationComponent appComponent;

    public static ApplicationComponent getAppComponent(Context context) {
        return ((App) context.getApplicationContext()).appComponent;
    }
}
