package lt.petraslabutis.testio.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.realm.Realm
import io.realm.RealmResults
import lt.petraslabutis.testio.api.AppService
import lt.petraslabutis.testio.api.model.ServerResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServerListViewModel @Inject constructor(
    private val appService: AppService
) : ViewModel() {

    fun refreshServerData(): Completable {
        return appService
            .getServers()
            .doOnNext { response ->
                Realm.getDefaultInstance().executeTransaction {
                    it.delete(ServerResponse::class.java)
                    it.copyToRealm(response)
                }
            }.ignoreElements()
    }

    fun getServerList(): Flowable<RealmResults<ServerResponse>> {
        return Realm.getDefaultInstance().where(ServerResponse::class.java).findAllAsync().asFlowable()
    }

}