package com.teso.net.data_flow.converters

import com.teso.net.data_flow.database.entities.ServerEntity
import com.teso.net.data_flow.network.api_models.ServerAnswer

object ServerConverter : Converter<ServerAnswer?, ServerEntity> {
    override fun convert(t: ServerAnswer?): ServerEntity {
        if (t == null || t.name == null) throw ConvertExeption("ServerAnswer or it's id is null")
        return ServerEntity(t.name, t.distance ?: 0)
    }
}