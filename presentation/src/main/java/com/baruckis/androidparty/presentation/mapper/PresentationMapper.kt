package com.baruckis.androidparty.presentation.mapper

interface PresentationMapper<PresentationModel, DomainEntity> {

    fun mapToPresentation(domainEntity: DomainEntity): PresentationModel

}