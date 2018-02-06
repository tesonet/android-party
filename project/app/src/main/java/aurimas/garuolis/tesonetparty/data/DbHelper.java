package aurimas.garuolis.tesonetparty.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Telephony;

/**
 * Created by Aurimas on 2018.02.05.
 */

public class DbHelper extends SQLiteOpenHelper {
    private final static String DB_NAME     = "party.db";
    private final static int DB_VERSION     = 1;

    public DbHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String query = "CREATE TABLE " + PartyContract.ServerEntry.TABLE_NAME + " ("
                + PartyContract.ServerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PartyContract.ServerEntry.COL_NAME + " TEXT NOT NULL,"
                + PartyContract.ServerEntry.COL_DISTANCE + " REAL NOT NULL"
                + ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE " + PartyContract.ServerEntry.TABLE_NAME);
            onCreate(db);
        }
    }
}
