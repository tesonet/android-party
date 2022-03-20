package com.example.domain_login.data.mapper

import com.example.core.mapper.Mapper
import com.example.domain_login.data.dto.TokenDto
import com.example.domain_login.domain.model.Token

class DtoToDomainMapper : Mapper<TokenDto, Token> {
    override fun map(from: TokenDto): Token = Token(token = from.token)
}