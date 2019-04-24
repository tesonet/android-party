package net.justinas.tesonetapp.withlib.domain

import io.reactivex.Single
import net.justinas.minilist.domain.item.IdEntity
import net.justinas.minilist.domain.item.ListRepository
import net.justinas.minilist.domain.item.PagedResponse
import net.justinas.tesonetapp.withlib.domain.remote.TesonetApi

class ListRepositoryRemote(val api: TesonetApi) : ListRepository {
    override fun getItems(page: Number): Single<List<IdEntity>> {
        return api.getList().map {
            it.map { element ->
                IdEntity(0, element.name, "${element.distance} km")
            }
        }
    }

    override fun search(page: Number): Single<PagedResponse> {
        return api.getList().map {PagedResponse(list =
            it.map { element ->
                IdEntity(0, element.name, "${element.distance} km")
            })
        }
    }

}