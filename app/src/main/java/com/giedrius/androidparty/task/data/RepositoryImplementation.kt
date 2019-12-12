package com.giedrius.androidparty.task.data

import android.content.SharedPreferences
import com.giedrius.androidparty.task.utils.ApiListener
import com.giedrius.androidparty.task.viewmodel.Token
import com.giedrius.androidparty.task.viewmodel.Server
import com.giedrius.androidparty.task.viewmodel.LoginBody
import com.giedrius.androidparty.task.server.login.LoginClient
import com.giedrius.androidparty.task.server.servers.ServersClient
import com.giedrius.androidparty.task.server.servers.ServersClientImplementation
import com.giedrius.androidparty.task.server.login.LoginOutcome
import com.giedrius.androidparty.task.utils.Constants

class RepositoryImplementation(private val loginClient: LoginClient, val sharedPreferences: SharedPreferences) : Repository {

    override fun getToken(username: String, password: String, apiListener: ApiListener<LoginOutcome>) {
        loginClient.login(LoginBody(username, password), object : ApiListener<Token> {
            override fun <T> onResult(data: T) {
                if (data is Token) {
                    sharedPreferences.edit().putString(Constants.TOKEN_KEY_IN_SHARED_PREFERENCES, data.token).apply()
                    apiListener.onResult(LoginOutcome.SUCCESSFUL)
                } else { apiListener.onResult(LoginOutcome.UNSUCCESSFUL) }
            }
        })
    }

    override fun getServers(apiListener: ApiListener<List<Server>>) {
        //This should be reused in order to prevent user from entering credentials everytime.
        //If saved token value is not null - launch servers list from the start of application and skip login
        val token = sharedPreferences.getString(Constants.TOKEN_KEY_IN_SHARED_PREFERENCES, null)
        val mServersClient: ServersClient = ServersClientImplementation(token!!)
        mServersClient.getServersList(object : ApiListener<List<Server>> {
            override fun <T> onResult(data: T) {
                val servers = data
                apiListener.onResult(servers)
            }
        })
    }

    companion object {
        private var instance: RepositoryImplementation? = null
        fun getInstance(loginClient: LoginClient, sharedPreferences: SharedPreferences): RepositoryImplementation {
            if (instance == null) { instance = RepositoryImplementation(loginClient, sharedPreferences) }
            return instance!!
        }
    }
}