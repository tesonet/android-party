package com.tzemaitis.androidparty;

import android.app.Application;

/**
 * Created by tzemaitis on 25/07/18.
 */

public class PartyApplication extends Application {

    private TesonetApiInterface api;

    @Override
    public void onCreate() {
        super.onCreate();

        api = TesonetApiClient.getClient().create(TesonetApiInterface.class);
    }

    TesonetApiInterface getRestApi() {
        return api;
    }
}
