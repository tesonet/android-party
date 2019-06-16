package com.mariussavickas.android_party

import com.codecave.outmatch.shared.exceptions.HttpCodeException
import org.junit.Test

import org.junit.Assert.*

class UtilTest {

    @Test
    fun httpCodeException_containsCode() {
        val exception = HttpCodeException(404)
        assertEquals(exception.code, 404)
    }
}
