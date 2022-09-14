package com.simplekjl.data.repository

import com.simplekjl.data.client.ClientService

interface NetworkSource {
    fun getClient(): ClientService
}
