package com.czech.androidparty.datasource.cache

import com.czech.androidparty.Database

class AndroidPartyDatabaseFactory(private val driverFactory: DriverFactory) {

    fun createDriver(): Database {
        return Database(
            driver = driverFactory.createDriver()
        )
    }
}