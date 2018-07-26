package com.axborn.androidparty.features.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.axborn.androidparty.features.utils.User;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseManager extends SQLiteOpenHelper {

    private final static int    DB_VERSION = 10;

    public DatabaseManager(Context context) {
        super(context, "myApp.db", null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        executeQuery(sqLiteDatabase, "create table logins (userId Integer primary key autoincrement, "+
                " username text, password text)");
        executeQuery(sqLiteDatabase, "create table servers (serverId Integer primary key autoincrement, "+
                " name text, distance text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try{
            System.out.println("UPGRADE DB oldVersion="+oldVersion+" - newVersion="+newVersion);
            onCreate(sqLiteDatabase);
            if (oldVersion<10){
                executeQuery(sqLiteDatabase, "create table logins (userId Integer primary key autoincrement, "+
                        " username text, password text)");
                executeQuery(sqLiteDatabase, "create table servers (serverId Integer primary key autoincrement, "+
                        " name text, distance text)");
            }
        }
        catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // super.onDowngrade(db, oldVersion, newVersion);
        System.out.println("DOWNGRADE DB oldVersion="+oldVersion+" - newVersion="+newVersion);
    }

    public User insertUser (User queryValues){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", queryValues.username);
        values.put("password", queryValues.password);
        queryValues.userId=database.insert("logins", null, values);
        database.close();
        return queryValues;
    }

    public void insertServer (String name, String distance){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("distance", distance);
        database.insert("servers", null, values);
        database.close();
    }

    public int updateUserPassword (User queryValues){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", queryValues.username);
        values.put("password", queryValues.password);
        queryValues.userId=database.insert("logins", null, values);
        database.close();
        return database.update("logins", values, "userId = ?", new String[] {String.valueOf(queryValues.userId)});
    }

    public User getUser (String username){
        String query = "Select userId, password from logins where username ='"+username+"'";
        User myUser = new User(0,username,"");
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                myUser.userId=cursor.getLong(0);
                myUser.password=cursor.getString(1);
            } while (cursor.moveToNext());
        }
        return myUser;
    }

    public ArrayList<HashMap<String, String>> getServers (){
        String query = "Select name, distance from servers";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        ArrayList<HashMap<String, String>> serverList = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> server = new HashMap<>();
                server.put("name", cursor.getString(0));
                server.put("distance", cursor.getString(1));
                serverList.add(server);
            } while (cursor.moveToNext());
        }
        return serverList;
    }

    private void executeQuery(SQLiteDatabase sqLiteDatabase, String query){
        sqLiteDatabase.execSQL(query);
    }

    public void cleanServerList() {
        SQLiteDatabase database = this.getReadableDatabase();
        executeQuery(database, "DELETE FROM servers ");
    }
}