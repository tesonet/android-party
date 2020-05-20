package com.baruckis.data.mapper

interface DataMapper<DataModel, DomainEntity> {

    fun mapFrom(dataModel: DataModel): DomainEntity

    fun mapTo(domainEntity: DomainEntity): DataModel

}