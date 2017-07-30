package com.romiope.testapp;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.romiope.testapp.database.AppDatabase;

public class App extends Application {

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(this, AppDatabase.class, "database").build();
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
