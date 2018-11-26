package com.tesonet.testio.util.networkavailability


class NetworkNotAvailableAvailability: NetworkAvailability {
    override fun isNetworkAvailable() = false
}