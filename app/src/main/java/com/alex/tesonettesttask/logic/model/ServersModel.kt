package com.alex.tesonettesttask.logic.model

import com.alex.tesonettesttask.logic.entities.Server
import com.alex.tesonettesttask.logic.network.TesonetService
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmResults
import retrofit2.Response
import javax.inject.Inject

class ServersModel @Inject constructor(private val tesonetService: TesonetService,
                                       private val realm: Realm) {

    fun fetchServers(): Observable<Response<List<Server>>> {
        return tesonetService.getServers()
    }

    fun saveServers(servers: List<Server>) {
        realm.executeTransaction { realm.insertOrUpdate(servers) }
    }

    fun getCachedServers(): RealmResults<Server> = realm.where(Server::class.java).findAll()

    fun hasCachedServers(): Boolean {
        return Realm.getDefaultInstance().where(Server::class.java).count() > 0
    }

    fun deleteServers() {
        realm.executeTransaction { getCachedServers().deleteAllFromRealm() }
    }


}