package com.baruckis.androidparty.remote.mapper

interface ApiResponseMapper<RemoteModel, DataModel> {

    fun mapFrom(remote: RemoteModel): DataModel
}