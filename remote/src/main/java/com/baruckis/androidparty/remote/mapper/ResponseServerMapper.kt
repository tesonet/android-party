package com.baruckis.androidparty.remote.mapper

import com.baruckis.androidparty.data.model.ServerData
import com.baruckis.androidparty.remote.model.ResponseServer
import javax.inject.Inject

class ResponseServerMapper @Inject constructor() :
    ApiResponseMapper<ResponseServer, ServerData> {

    override fun mapFrom(remote: ResponseServer): ServerData {
        return ServerData(remote.name, remote.distance)
    }

}