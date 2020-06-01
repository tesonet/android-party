/*
 * Copyright 2020 Andrius Baruckis www.baruckis.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.baruckis.androidparty.remote

import com.baruckis.androidparty.data.model.ServerData
import com.baruckis.androidparty.data.model.TokenData
import com.baruckis.androidparty.data.repository.RemoteDataSource
import com.baruckis.androidparty.remote.api.TesonetApiService
import com.baruckis.androidparty.remote.mapper.ResponseServerMapper
import com.baruckis.androidparty.remote.mapper.ResponseTokenMapper
import com.baruckis.androidparty.remote.model.RequestUser
import com.baruckis.androidparty.remote.model.ResponseServer
import com.baruckis.androidparty.remote.model.ResponseToken
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: TesonetApiService,
    private val apiResponseTokenMapper: ResponseTokenMapper,
    private val apiResponseServerMapper: ResponseServerMapper
) : RemoteDataSource {

    override fun sendAuthorization(username: String, password: String): Single<TokenData> {
        return apiService.sendAuthorization(
            RequestUser(
                username,
                password
            )
        )
            .map { response: ResponseToken ->
                apiResponseTokenMapper.mapFromRemote(response)
            }
    }

    override fun getServers(): Single<List<ServerData>> {
        return apiService.getServers().map { response: List<ResponseServer> ->
            response.map { item: ResponseServer -> apiResponseServerMapper.mapFromRemote(item) }
        }
    }

}