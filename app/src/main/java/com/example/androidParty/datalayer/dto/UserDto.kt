package com.example.androidParty.datalayer.dto

import com.example.androidParty.presentation.login.domain.entity.User

data class UserDto(val token: String)

fun UserDto.toUser(): User {
    return User(token = token)
}