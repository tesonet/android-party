package com.example.partyapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.partyapp.R;
import com.example.partyapp.Models.Server;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "party.db";
    private static final int DATABASE_VERSION = 17;

    private Dao<Server, Integer> serverDao;
//    private Dao<LogItem, Integer> logDao;
//    private Dao<DayItem, Integer> dayDao;
//    private Dao<Recipe, Integer> recipeDao;
//    private Dao<RecipeFood, Integer> recipeFoodDao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            //Create tables
            TableUtils.createTable(connectionSource, Server.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to create databases", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {
            TableUtils.dropTable(connectionSource, Server.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new "
                    + newVer, e);
        }
    }

    public void clearServersTable() {
        try {
            TableUtils.clearTable(getConnectionSource(), Server.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to delete databases", e);
        }
    }

    //add or get food list using dao
    public Dao<Server, Integer> getServersDao() throws SQLException {
        if (serverDao == null) {
            serverDao = getDao(Server.class);
        }
        return serverDao;
    }
}
