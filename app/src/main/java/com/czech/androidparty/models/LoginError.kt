package com.czech.androidparty.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginError(
    val message: String
)
