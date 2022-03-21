package com.example.domainLogin.utils

import com.example.domainLogin.data.dto.TokenDto
import com.example.domainLogin.domain.model.Token

object TestData {

    fun getTokenDto() = TokenDto(
        token = "test_token"
    )

    fun getToken() = Token(
        token = "test_token"
    )
}
