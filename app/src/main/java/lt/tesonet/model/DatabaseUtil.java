package lt.tesonet.model;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseUtil {

    private static ServerDatabase serverDatabase;

    private DatabaseUtil() {
    }

    public static ServerDatabase getServerDatabase(Context context) {
        if (serverDatabase == null) {
            serverDatabase = Room.databaseBuilder(context.getApplicationContext(), ServerDatabase.class, "servers").build();
        }

        return serverDatabase;
    }
}
