package com.baruckis.androidparty.data.mapper

interface DataMapper<DataModel, DomainEntity> {

    fun mapFromData(dataModel: DataModel): DomainEntity

    fun mapToData(domainEntity: DomainEntity): DataModel

}