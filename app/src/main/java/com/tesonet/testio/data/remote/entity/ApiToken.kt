package com.tesonet.testio.data.remote.entity

data class ApiToken(
    val token: String
) {
    override fun toString() = "Bearer $token"
}
