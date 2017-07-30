package com.romiope.testapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.romiope.testapp.network.entities.ServerResponse;

@Database(entities = {ServerResponse.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ServersDao serversDao();
}
