package com.njakawaii.tesonet;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.njakawaii.tesonet.data.ServersDB;
import com.njakawaii.tesonet.network.ModelService;
import com.njakawaii.tesonet.network.RestService;
import com.orhanobut.hawk.Hawk;

import timber.log.Timber;

public class App extends Application {
    private static App instance;
    private Context context;
    private Handler handler;
    private RestService restService;
    private ModelService modelService;

    private ServersDB database;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        handler = new Handler(Looper.getMainLooper());
        context = getApplicationContext();
        Hawk.init(context).build();
        Timber.plant(new Timber.DebugTree());
        database = Room.databaseBuilder(this, ServersDB.class, "servers")
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        if (instance == null) {
            throw new RuntimeException("Application initialization error!");
        }
        return instance;
    }

    public ServersDB getDatabase() {
        return database;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public void runOnUiThread(Runnable runnable){
        handler.post(runnable);
    }

    public ModelService getModelService() {
        if (modelService==null){
            modelService = new ModelService();
        }
        return modelService;
    }

}
