package com.yasserakbbach.androidparty.login.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Session(
    @SerialName("token") val token: String? = null,
)
