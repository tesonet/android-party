package com.example.domainLogin.data.datasource.local

import com.example.domainLogin.data.datastore.LocalSharedPreference
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase
import org.junit.Test

class LocalDataSourceImplTest : TestCase() {
    private val pref: LocalSharedPreference = mockk()
    private val dataSource = LocalDataSourceImpl(pref)
    val input = "token"

    @Test
    fun `test saveToken return unit`() {
        every {
            pref.saveToken(any())
        } returns Unit

        dataSource.saveToken(input)
        verify(exactly = 1) { dataSource.saveToken(input) }
    }
}