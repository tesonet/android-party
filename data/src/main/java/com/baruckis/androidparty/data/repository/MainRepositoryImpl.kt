package com.baruckis.androidparty.data.repository

import com.baruckis.androidparty.data.mapper.LoggedInUserMapper
import com.baruckis.androidparty.data.mapper.ServerMapper
import com.baruckis.androidparty.data.model.LoggedInUserData
import com.baruckis.androidparty.data.model.TokenData
import com.baruckis.androidparty.domain.entity.LoggedInUserEntity
import com.baruckis.androidparty.domain.entity.ServerEntity
import com.baruckis.androidparty.domain.repository.MainRepository
import io.reactivex.Single
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val loggedInUserMapper: LoggedInUserMapper,
    private val serverMapper: ServerMapper,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MainRepository {

    override fun getLoggedInUser(): LoggedInUserEntity? {
        return localDataSource.getLoggedInUser()?.let { loggedInUserMapper.mapFromData(it) }
    }

    override fun login(username: String, password: String): Single<LoggedInUserEntity> {
        return remoteDataSource.sendAuthorization(username, password).map { tokenData: TokenData ->
            val loggedInUserData = LoggedInUserData(tokenData.token, username)
            localDataSource.setLoggedInUser(loggedInUserData)
            loggedInUserMapper.mapFromData(loggedInUserData)
        }
    }

    override fun logout() {
        localDataSource.setLoggedInUser(null)
    }

    override fun fetchServersFromRemoteApiSaveToDb(): Single<List<ServerEntity>> {

        return remoteDataSource.getServers().flatMap { serversList ->

            localDataSource.clearServers().andThen(
                localDataSource.saveServers(serversList).andThen(Single.just(serversList))
            )

        }.map { serversList ->
            serversList.map { server ->
                serverMapper.mapFromData(server)
            }
        }

    }

    override fun fetchServersFromLocalCache(): Single<List<ServerEntity>> {
        return localDataSource.getServers().map { serversList ->
            serversList.map { server -> serverMapper.mapFromData(server) }
        }
    }

}