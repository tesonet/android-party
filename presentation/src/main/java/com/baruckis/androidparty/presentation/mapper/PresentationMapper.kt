package com.baruckis.androidparty.presentation.mapper

interface PresentationMapper<PresentationModel, DomainEntity> {

    fun mapTo(domainEntity: DomainEntity): PresentationModel

}