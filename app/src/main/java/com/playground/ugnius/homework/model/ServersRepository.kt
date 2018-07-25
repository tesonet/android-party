package com.playground.ugnius.homework.model

import com.playground.ugnius.homework.model.entities.Server
import io.realm.Realm
import javax.inject.Inject

open class ServersRepository @Inject constructor(private val realm: Realm) {

    fun saveServers(servers: List<Server>) {
        realm.executeTransactionAsync { it.copyToRealm(servers) }
    }

    fun getServers() = realm.where(Server::class.java).findAll()

    fun clear() = realm.executeTransaction { getServers().deleteAllFromRealm() }
}