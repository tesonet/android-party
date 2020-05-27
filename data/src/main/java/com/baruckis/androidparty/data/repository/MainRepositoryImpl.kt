package com.baruckis.androidparty.data.repository

import com.baruckis.androidparty.data.mapper.LoggedInUserMapper
import com.baruckis.androidparty.data.mapper.ServerMapper
import com.baruckis.androidparty.data.model.LoggedInUserData
import com.baruckis.androidparty.data.model.ServerData
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
        return localDataSource.getLoggedInUser()?.let { loggedInUserMapper.mapFrom(it) }
    }

    override fun login(username: String, password: String): Single<LoggedInUserEntity> {
        return remoteDataSource.sendAuthorization(username, password).map { tokenData: TokenData ->
            val loggedInUserData = LoggedInUserData(tokenData.token, username)
            localDataSource.setLoggedInUser(loggedInUserData)
            loggedInUserMapper.mapFrom(loggedInUserData)
        }
    }

    override fun logout() {
        localDataSource.setLoggedInUser(null)
    }

    override fun getServers(): Single<List<ServerEntity>> {
        return remoteDataSource.getServers().map { response: List<ServerData> ->
            response.map { item: ServerData -> serverMapper.mapFrom(item) }
        }
    }

}