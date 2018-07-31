package lt.tesonet.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Server.class}, version = 1)
public abstract class ServerDatabase extends RoomDatabase {
    public abstract ServerDao getDao();
}
