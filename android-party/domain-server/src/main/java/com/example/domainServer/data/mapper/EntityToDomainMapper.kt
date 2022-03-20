package com.example.domainServer.data.mapper

import com.example.core.mapper.Mapper
import com.example.domainServer.data.db.entity.ServerEntity
import com.example.domainServer.domain.model.Server

class EntityToDomainMapper : Mapper<ServerEntity, Server> {
    override fun map(from: ServerEntity): Server {
        return Server(
            name = from.name,
            distance = from.distance
        )
    }
}
