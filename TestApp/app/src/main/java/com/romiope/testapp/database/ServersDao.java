package com.romiope.testapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.romiope.testapp.network.entities.ServerResponse;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ServersDao {

    @Query("SELECT * FROM ServerResponse")
    List<ServerResponse> getAllServers();

    @Insert(onConflict = REPLACE)
    void insertServers(List<ServerResponse> server);

    @Query("DELETE FROM ServerResponse")
    void clean();
}
