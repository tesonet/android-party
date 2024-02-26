package test.core

import feature.main.data.model.Server
import org.junit.Assert
import java.util.Locale

fun assertServers(servers: List<Server>) {
    Assert.assertEquals(30, servers.size)
    val countries = Locale.getISOCountries().map { Locale("", it).displayCountry }
    val countryName = "country"
    val numberName = "number"
    servers.forEach {
        val matchResult = """(?<$countryName>.+) #(?<$numberName>\d{1,2})"""
            .toRegex()
            .find(it.name)!!
        val country = matchResult.groups[countryName]!!.value
        val number = matchResult.groups[numberName]!!.value.toInt()
        Assert.assertTrue(countries.contains(country))
        Assert.assertTrue(number in 0..100)
        Assert.assertTrue(it.distance in 0..2_000)
    }
}