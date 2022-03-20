package com.example.domainLogin.data.mapper

import com.example.core.mapper.Mapper
import com.example.domainLogin.data.dto.TokenDto
import com.example.domainLogin.domain.model.Token

class DtoToDomainMapper : Mapper<TokenDto, Token> {
    override fun map(from: TokenDto): Token = Token(token = from.token)
}
