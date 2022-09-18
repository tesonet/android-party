package com.ac.androidparty.login.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Token(@SerialName("token") val token: String)