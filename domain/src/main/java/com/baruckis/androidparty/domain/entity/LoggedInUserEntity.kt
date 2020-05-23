package com.baruckis.androidparty.domain.entity

data class LoggedInUserEntity(
    var token: String,
    var username: String
) {
}