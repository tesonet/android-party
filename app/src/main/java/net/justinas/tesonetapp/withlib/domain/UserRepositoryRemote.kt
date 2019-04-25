package net.justinas.tesonetapp.withlib.domain

import android.content.Context
import android.content.Context.MODE_PRIVATE
import io.reactivex.Completable
import net.justinas.minilist.domain.user.UserRepository
import net.justinas.tesonetapp.withlib.domain.remote.TesonetApi
import net.justinas.tesonetapp.withlib.network.HeaderInterceptor

class UserRepositoryRemote(val api: TesonetApi, val interactor: HeaderInterceptor, val context: Context) :
    UserRepository {

    override fun login(username: String, password: String): Completable {
        return api.login(username, password)
            .flatMapCompletable { token ->
                interactor.token = token.token
                save(token.token)
            }
    }

    override fun save(token: String): Completable {
        return Completable.fromAction {
            context.getSharedPreferences("User", MODE_PRIVATE).edit().putString("token", token).apply()
        }
    }

    override fun logout(): Completable {
        return Completable.fromAction {
            interactor.token = null
            context.getSharedPreferences("User", MODE_PRIVATE).edit().remove("token").apply()
        }
    }
}