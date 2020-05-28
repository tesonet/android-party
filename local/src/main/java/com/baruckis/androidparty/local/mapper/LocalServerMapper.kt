package com.baruckis.androidparty.local.mapper

import com.baruckis.androidparty.data.model.ServerData
import com.baruckis.androidparty.local.model.ServerLocal
import javax.inject.Inject

class LocalServerMapper @Inject constructor() : LocalMapper<ServerLocal, ServerData> {

    override fun mapFromLocal(localModel: ServerLocal): ServerData {
        return ServerData(localModel.name, localModel.distance)
    }

    override fun mapToLocal(dataModel: ServerData): ServerLocal {
        return ServerLocal(name = dataModel.name, distance = dataModel.distance)
    }

}