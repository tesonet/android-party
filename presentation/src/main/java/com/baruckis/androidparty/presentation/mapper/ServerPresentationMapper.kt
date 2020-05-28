package com.baruckis.androidparty.presentation.mapper

import com.baruckis.androidparty.domain.entity.ServerEntity
import com.baruckis.androidparty.presentation.model.ServerPresentation
import javax.inject.Inject

class ServerPresentationMapper @Inject constructor() :
    PresentationMapper<ServerPresentation, ServerEntity> {

    override fun mapToPresentation(domainEntity: ServerEntity): ServerPresentation {
        return ServerPresentation(
            domainEntity.name, domainEntity.distance
        )
    }

}