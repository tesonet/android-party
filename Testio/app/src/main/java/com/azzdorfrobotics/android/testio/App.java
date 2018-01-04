package com.azzdorfrobotics.android.testio;

import android.app.Application;
import android.content.Context;
import com.azzdorfrobotics.android.testio.di.AppComponent;
import com.azzdorfrobotics.android.testio.di.AppModule;
import com.azzdorfrobotics.android.testio.di.DaggerAppComponent;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

public class App extends Application {

    static Context context;
    AppComponent appComponent;

    @Override public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public static Context getApplication() {
        return context;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static int getVersionCode() {
        return BuildConfig.VERSION_CODE;
    }
}
