package com.czech.androidparty.models

import kotlinx.serialization.Serializable

@Serializable
class DataList(
    val name: String,
    val distance: Int
)