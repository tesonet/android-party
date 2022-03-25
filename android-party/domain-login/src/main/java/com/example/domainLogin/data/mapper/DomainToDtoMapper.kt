package com.example.domainLogin.data.mapper

import com.example.core.mapper.Mapper
import com.example.domainLogin.data.dto.LoginDto
import com.example.domainLogin.domain.model.LoginInfo

class DomainToDtoMapper : Mapper<LoginInfo, LoginDto> {
    override fun map(from: LoginInfo): LoginDto =
        LoginDto(userName = from.userName, password = from.password)
}
