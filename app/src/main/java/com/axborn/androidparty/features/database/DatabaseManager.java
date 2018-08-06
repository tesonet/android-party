package com.axborn.androidparty.features.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseManager extends SQLiteOpenHelper {

    private final static int    DB_VERSION = 10;

    public DatabaseManager(Context context) {
        super(context, "myApp.db", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        executeQuery(sqLiteDatabase, "CREATE TABLE logins (userId INTEGER PRIMARY KEY AUTOINCREMENT, "+
                " username TEXT, password TEXT)");
        executeQuery(sqLiteDatabase, "CREATE TABLE servers (serverId INTEGER PRIMARY KEY AUTOINCREMENT, "+
                " name TEXT, distance TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try{
            System.out.println("UPGRADE DB oldVersion="+oldVersion+" - newVersion="+newVersion);
            onCreate(sqLiteDatabase);
            if (oldVersion<10){
                executeQuery(sqLiteDatabase, "CREATE TABLE logins (userId INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        " username TEXT, password TEXT)");
                executeQuery(sqLiteDatabase, "CREATE TABLE servers (serverId INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        " name TEXT, distance TEXT)");
            }
        }
        catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // super.onDowngrade(db, oldVersion, newVersion);
        System.out.println("DOWNGRADE DB oldVersion="+oldVersion+" - newVersion="+newVersion);
    }

    public void insertServer (String name, String distance){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("distance", distance);
        database.insert("servers", null, values);
        database.close();
    }


    public ArrayList<HashMap<String, String>> getServers (){
        String query = "SELECT name, distance FROM servers";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        ArrayList<HashMap<String, String>> serverList = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> server = new HashMap<>();
                server.put("name", cursor.getString(0));
                server.put("distance", cursor.getString(1) + " km");
                serverList.add(server);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return serverList;
    }

    private void executeQuery(SQLiteDatabase sqLiteDatabase, String query){
        sqLiteDatabase.execSQL(query);
    }

    public void cleanServerList() {
        SQLiteDatabase database = this.getReadableDatabase();
        executeQuery(database, "DELETE FROM servers");
    }
}