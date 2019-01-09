package lt.zilinskas.marius.testio.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import static lt.zilinskas.marius.testio.database.ServersTable.COLUMN_ID;
import static lt.zilinskas.marius.testio.database.ServersTable.COLUMN_NAME;
import static lt.zilinskas.marius.testio.database.ServersTable.COLUMN_DISTANCE;

class ServersCursorWrapper extends CursorWrapper {
    private final int iServerId;
    private final int iName;
    private final int iDistance;

    ServersCursorWrapper(Cursor cursor) {
        super(cursor);
        iServerId = cursor.getColumnIndex(COLUMN_ID);
        iName = cursor.getColumnIndex(COLUMN_NAME);
        iDistance = cursor.getColumnIndex(COLUMN_DISTANCE);
    }

    int getServerId() {
        return getInt(iServerId);
    }

    String getName() {
        return getString(iName);
    }

    int getDistance() {
        return getInt(iDistance);
    }

}