package com.example.tesonet.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.tesonet.database.daos.ServerDao;
import com.example.tesonet.database.models.Server;

@Database(entities = Server.class, version = 1)
public abstract class ServerDatabase extends RoomDatabase {
    private static ServerDatabase instance;

    public abstract ServerDao serverDao();

    public static synchronized ServerDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ServerDatabase.class, "server_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new ServerDatabase.PopulateDBAsyncTask(instance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private ServerDao serverDao;

        private PopulateDBAsyncTask(ServerDatabase db) {
            serverDao = db.serverDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            serverDao.insert(new Server("Vilnius #3", 1000));
            return null;
        }
    }
}
