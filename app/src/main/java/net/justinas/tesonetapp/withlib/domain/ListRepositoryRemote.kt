package net.justinas.tesonetapp.withlib.domain

import io.reactivex.Single
import net.justinas.minilist.domain.IdEntity
import net.justinas.minitemplate.domain.ListRepository
import net.justinas.tesonetapp.withlib.domain.remote.TesonetApi

class ListRepositoryRemote(val api: TesonetApi) : ListRepository {

    override fun getItems(): Single<List<IdEntity>> {
        return api.getList().map {
            it.map { element ->
                IdEntity(0, element.name, "${element.distance} km")
            }
        }
    }
}