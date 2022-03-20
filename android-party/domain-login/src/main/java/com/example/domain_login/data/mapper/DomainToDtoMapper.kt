package com.example.domain_login.data.mapper

import com.example.core.mapper.Mapper
import com.example.domain_login.data.dto.LoginDto
import com.example.domain_login.domain.model.LoginInfo

class DomainToDtoMapper : Mapper<LoginInfo, LoginDto> {
    override fun map(from: LoginInfo): LoginDto =
        LoginDto(userName = from.userName, password = from.password)
}