package com.example.tesonet.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tesonet.database.models.Server;

import java.util.List;

@Dao
public interface ServerDao {

    @Insert
    void insert(Server server);

    @Query("DELETE FROM servers_table")
    void deleteAllServers();

    @Query("SELECT * FROM servers_table")
    List<Server> getAllServers();
}
