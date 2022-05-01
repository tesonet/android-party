package com.czech.androidparty.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String? = null,
    val message: String? = null
)
