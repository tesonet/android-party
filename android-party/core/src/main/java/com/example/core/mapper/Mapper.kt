package com.example.core.mapper

interface Mapper<FROM, TO> {
    fun map(from: FROM): TO
}
