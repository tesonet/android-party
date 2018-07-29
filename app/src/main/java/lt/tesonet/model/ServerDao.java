package lt.tesonet.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ServerDao {

    @Insert(onConflict = REPLACE)
    void addAll(List<Server> servers);

    @Query("SELECT * FROM server")
    Single<List<Server>> getAllServers();

    @Query("DELETE FROM server")
    void clearServers();
}
