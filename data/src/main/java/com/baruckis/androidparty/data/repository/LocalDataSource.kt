package com.baruckis.androidparty.data.repository

import com.baruckis.androidparty.data.model.LoggedInUserData
import com.baruckis.androidparty.data.model.ServerData
import io.reactivex.Completable
import io.reactivex.Single

interface LocalDataSource {

    fun getToken(): String

    fun getLoggedInUser(): LoggedInUserData?

    fun setLoggedInUser(user: LoggedInUserData?)

    fun getServers(): Single<List<ServerData>>

    fun clearServers(): Completable

    fun saveServers(serversList: List<ServerData>): Completable

}