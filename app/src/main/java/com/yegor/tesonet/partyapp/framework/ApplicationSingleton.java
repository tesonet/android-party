package com.yegor.tesonet.partyapp.framework;

import android.app.Application;

import com.yegor.tesonet.partyapp.networking.ServerApi;

/**
 * Application representation
 */
public class ApplicationSingleton extends Application {

    private static ApplicationSingleton sApplication;
    private ServerApi mApi;

    /**
     * @return instance of current app context
     */
    public static ApplicationSingleton getInstance() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        mApi = new ServerApi();
    }

    public ServerApi getApi(){
        return mApi;
    }
}
