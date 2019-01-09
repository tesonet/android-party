package lt.zilinskas.marius.testio.database;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;

public class DatabaseUtils {

    private DatabaseUtils() {
    }

    public static SQLiteStatement makeInsertOrReplaceStatement(SQLiteDatabase db, String tableName, String[] columns) {
        String insertOrReplaceStatementQuery = "INSERT OR REPLACE INTO "
                + tableName + " (" + TextUtils.join(", ", columns)
                + ") VALUES (" + DatabaseUtils.makeHooksForValues(columns.length) + ")";
        return db.compileStatement(insertOrReplaceStatementQuery);
    }

    private static String makeHooksForValues(int count) {
        StringBuilder hooks = new StringBuilder();
        hooks.append("?");
        for (int i = 1; i < count; i++) {
            hooks.append(",?");
        }
        return hooks.toString();
    }
}
