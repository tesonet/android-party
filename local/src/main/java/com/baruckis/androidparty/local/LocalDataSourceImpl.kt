package com.baruckis.androidparty.local

import com.baruckis.androidparty.data.model.LoggedInUserData
import com.baruckis.androidparty.data.model.ServerData
import com.baruckis.androidparty.data.repository.LocalDataSource
import com.baruckis.androidparty.local.database.AppDatabase
import com.baruckis.androidparty.local.mapper.LocalServerMapper
import com.baruckis.androidparty.local.preferences.PreferenceStorage
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val sharedPreferenceStorage: PreferenceStorage,
    private val appDatabase: AppDatabase,
    private val localServerMapper: LocalServerMapper
) : LocalDataSource {

    override fun getToken(): String {
        return sharedPreferenceStorage.token ?: ""
    }

    override fun getLoggedInUser(): LoggedInUserData? {
        val token = sharedPreferenceStorage.token
        val username = sharedPreferenceStorage.username
        return if (token.isNullOrBlank() || username.isNullOrBlank()) null else
            LoggedInUserData(token, username)
    }

    override fun setLoggedInUser(user: LoggedInUserData?) {
        sharedPreferenceStorage.token = user?.token
        sharedPreferenceStorage.username = user?.username
    }

    override fun getServers(): Single<List<ServerData>> {
        return appDatabase.serverLocalDao().getServers().firstOrError().map { serversList ->
            serversList.map { server -> localServerMapper.mapFromLocal(server) }
        }
    }

    override fun clearServers(): Completable {
        return Completable.defer {
            appDatabase.serverLocalDao().deleteServers()
            Completable.complete()
        }
    }

    override fun saveServers(serversList: List<ServerData>): Completable {
        return Completable.defer {
            appDatabase.serverLocalDao()
                .saveServers(serversList.map { server -> localServerMapper.mapToLocal(server) })
            Completable.complete()
        }
    }

}