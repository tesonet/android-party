package com.assignment.android_party2.servers.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.android_party2.servers.models.ServerModel

@Database(entities = [(ServerModel::class)], version = 1)
abstract class ServersDatabase : RoomDatabase(){
    abstract fun serversDao() : ServerDao
}