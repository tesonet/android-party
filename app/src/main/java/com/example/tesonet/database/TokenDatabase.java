package com.example.tesonet.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.tesonet.database.daos.TokenDao;
import com.example.tesonet.database.models.Token;

@Database(entities = Token.class, version = 1)
public abstract class TokenDatabase extends RoomDatabase {
    private static TokenDatabase instance;

    public abstract TokenDao tokenDao();

    public static synchronized TokenDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TokenDatabase.class, "token_database")
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
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private TokenDao tokenDao;

        private PopulateDBAsyncTask(TokenDatabase db) {
            tokenDao = db.tokenDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            tokenDao.insert(new Token("0"));
            return null;
        }
    }
}
