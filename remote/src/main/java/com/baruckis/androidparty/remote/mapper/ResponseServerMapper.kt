package com.baruckis.androidparty.remote.mapper

import com.baruckis.androidparty.data.model.ServerData
import com.baruckis.androidparty.remote.model.ResponseServer
import javax.inject.Inject

class ResponseServerMapper @Inject constructor() :
    ApiResponseMapper<ResponseServer, ServerData> {

    override fun mapFromRemote(remoteModel: ResponseServer): ServerData {
        return ServerData(remoteModel.name, remoteModel.distance)
    }

}