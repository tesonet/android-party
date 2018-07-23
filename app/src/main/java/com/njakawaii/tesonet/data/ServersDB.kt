package com.njakawaii.tesonet.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [(ServerModel::class)], version = 1)
 abstract class ServersDB : RoomDatabase() {

    abstract fun serverDao(): ServersDAO

}