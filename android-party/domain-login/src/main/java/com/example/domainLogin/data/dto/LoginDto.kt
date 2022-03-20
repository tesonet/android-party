package com.example.domainLogin.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    @SerialName(value = "username") val userName: String,
    @SerialName(value = "password") val password: String
)
