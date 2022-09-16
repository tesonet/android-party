package com.simplekjl.data.repository

import com.simplekjl.data.client.ClientService

class NetworkSourceImpl(private val client: ClientService) : NetworkSource {
    override fun getClient(): ClientService = client
}
