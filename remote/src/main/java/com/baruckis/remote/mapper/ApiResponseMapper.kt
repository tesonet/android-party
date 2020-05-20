package com.baruckis.remote.mapper

interface ApiResponseMapper<RemoteModel, DataModel> {

    fun mapFrom(remote: RemoteModel): DataModel
}