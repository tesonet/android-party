package lt.zilinskas.marius.testio.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import lt.zilinskas.marius.testio.api.entities.Server;
import lt.zilinskas.marius.testio.utils.CollectionUtils;

import static lt.zilinskas.marius.testio.database.ServersTable.COLUMN_DISTANCE;
import static lt.zilinskas.marius.testio.database.ServersTable.COLUMN_NAME;
import static lt.zilinskas.marius.testio.database.ServersTable.TABLE_SERVERS;

public class ServersDAO {
    private static final String TAG = ServersDAO.class.getName();

    private DatabaseHelper databaseHelper;

    public ServersDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void insertOrReplace(final List<Server> servers, boolean deleteOthers) {
        if (CollectionUtils.isEmpty(servers)) {
            return;
        }

        int i = 1;
        final int iName = i++;
        final int iDistance = i;
        String[] columns = {COLUMN_NAME, COLUMN_DISTANCE};

        SQLiteDatabase dbw = databaseHelper.getWritableDatabase();
        dbw.beginTransaction();
        try {
            if (deleteOthers) {
                deleteAllServers();
            }

            for (Server server : servers) {
                SQLiteStatement statement = DatabaseUtils.makeInsertOrReplaceStatement(dbw, TABLE_SERVERS, columns);
                statement.bindString(iName, server.getName());
                statement.bindDouble(iDistance, server.getDistance());
                statement.execute();
            }
            dbw.setTransactionSuccessful();
        } finally {
            dbw.endTransaction();
        }
    }

    public List<Server> getAllServers() {
        String query = "SELECT * FROM " + TABLE_SERVERS;

        SQLiteDatabase dbr = databaseHelper.getReadableDatabase();
        List<Server> servers = new ArrayList<>(0);
        try (Cursor cursor = dbr.rawQuery(query, null)) {
            ServersCursorWrapper serversCursor = new ServersCursorWrapper(cursor);

            if (!serversCursor.moveToFirst()) {
                return servers;
            }

            do {
                Server server = new Server();
                server.setName(serversCursor.getName());
                server.setDistance(serversCursor.getDistance());
                servers.add(server);
            } while (serversCursor.moveToNext());
        } catch (Exception e) {
            Log.e(TAG, "Unable to getAlExpenses", e);
        }

        return servers;
    }

    public boolean hasObjects() {
        String query = "SELECT 1 FROM " + TABLE_SERVERS + " LIMIT 1";

        SQLiteDatabase dbr = databaseHelper.getReadableDatabase();
        try (Cursor cursor = dbr.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                return true;
            }
        }

        return false;
    }

    public void deleteAllServers() {
        String query = "DELETE FROM " + TABLE_SERVERS;

        SQLiteDatabase dbw = databaseHelper.getWritableDatabase();
        dbw.beginTransaction();
        try {
            dbw.execSQL(query);
            dbw.setTransactionSuccessful();
        } finally {
            dbw.endTransaction();
        }
    }
}
