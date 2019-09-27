package com.example.tesonet.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tesonet.database.models.Token;

@Dao
public interface TokenDao {

    @Insert
    void insert(Token token);

    @Query("DELETE FROM token_table")
    void deleteToken();

    @Query("SELECT * FROM token_table")
    LiveData<Token> getTokenValue();
}

