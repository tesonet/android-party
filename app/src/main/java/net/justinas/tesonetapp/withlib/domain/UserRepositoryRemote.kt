package net.justinas.tesonetapp.withlib.domain

import io.reactivex.Completable
import net.justinas.minitemplate.domain.UserRepository
import net.justinas.tesonetapp.withlib.domain.remote.TesonetApi

class UserRepositoryRemote(val api: TesonetApi): UserRepository {

    override fun login(username: String, password: String) : Completable {
        return api.login(username, password)
            .flatMapCompletable { token ->
            save(token.token)
        }
    }

    override fun save(token: String): Completable {
        return Completable.complete()
    }
}