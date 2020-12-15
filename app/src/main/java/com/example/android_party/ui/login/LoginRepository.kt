package com.example.android_party.ui.login

import com.example.android_party.App
import com.example.android_party.api.TesonetApi
import com.example.android_party.data.Token
import com.example.android_party.data.AppDatabase
import io.reactivex.Observable

class LoginRepository {

    private val database: AppDatabase = App.database!!

    fun storeToken(token: Token) {
        database.tokenDao().delete()
        database.tokenDao().insert(token)
    }

    fun deleteToken() {
        database.tokenDao().delete()
    }

    fun getToken(): String {
        return if (database.tokenDao().get() == null) {
            ""
        } else {
            database.tokenDao().get().token
        }
    }

    fun getTokenFromApi(tesonetApi: TesonetApi, username: String, password: String): Observable<Token> {
        return tesonetApi.getAuthToken(username, password)
    }
}