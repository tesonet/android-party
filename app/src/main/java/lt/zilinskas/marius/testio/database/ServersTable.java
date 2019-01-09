package lt.zilinskas.marius.testio.database;

public class ServersTable {
    public static final String TABLE_SERVERS = "servers";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DISTANCE = "distance";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_SERVERS + " ( "
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_DISTANCE + " INT )";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_SERVERS;

    private ServersTable() {
    }
}
