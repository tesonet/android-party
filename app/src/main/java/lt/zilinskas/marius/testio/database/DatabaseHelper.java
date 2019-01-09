package lt.zilinskas.marius.testio.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Testio.db";
    private static final int DATABASE_VERSION = 1;
    private static DatabaseHelper instance;
    private ServersDAO serversDAO;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ServersTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ServersTable.DROP_TABLE);
        onCreate(db);
    }

    public ServersDAO getExpensesDAO() {
        if (serversDAO == null) {
            serversDAO = new ServersDAO(this);
        }
        return serversDAO;
    }

}
