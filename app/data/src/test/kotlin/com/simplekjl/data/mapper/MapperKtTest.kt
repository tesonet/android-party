package com.simplekjl.data.mapper

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.simplekjl.data.model.ServerDetailsRaw
import org.junit.Test

internal class MapperKtTest {

    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    @Test
    fun `when given an empty arrayListOf(ServerDetailsRaw) return emptyList`() {
        val result = arrayListOf<ServerDetailsRaw>().toModel()
        assert(result.isEmpty())
    }

    @Test
    fun `when given an arrayListOf(ServerDetailsRaw) return listOf(ServerDetails)`() {
        val testData: ArrayList<ServerDetailsRaw> = fixture()
        val result = testData.toModel()
        assert(result.isNotEmpty())
        assert(result.size == testData.size)
    }
}
