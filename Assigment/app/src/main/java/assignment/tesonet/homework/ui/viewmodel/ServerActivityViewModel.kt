package assignment.tesonet.homework.ui.viewmodel

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.os.AsyncTask
import assignment.tesonet.homework.App
import assignment.tesonet.homework.ServerResponse
import assignment.tesonet.homework.api.response.Server
import assignment.tesonet.homework.storage.AppPreferences
import assignment.tesonet.homework.storage.database.MyDatabase

class ServerActivityViewModel(val app : App) : AndroidViewModel(app), ViewModelResponse {

    override val loginResponse = MutableLiveData<Int>()
    var dataList: List<Server>? = null

    private val db = MyDatabase.getInstance(app)

    fun askServerForServerList() {
        AsyncTask.execute({
            dataList = db?.serverDao()?.load()
            when (dataList?.isNotEmpty()) {
                true -> {
                    loginResponse.postValue(ServerResponse.SUCCESS)
                    return@execute
                }
            }
            val token = AppPreferences.token
            val servers = app.service.getServers(token).execute()
            if (servers.isSuccessful) {
                dataList = servers.body()
                saveToDb()
            } else {
                loginResponse.postValue(ServerResponse.ERROR)
            }
        })
    }

    private fun saveToDb() {
        dataList?.let {
            db?.serverDao()?.save(it)
            loginResponse.postValue(ServerResponse.SUCCESS)
        }
    }

    fun logout() {
        AppPreferences.token = null
        AsyncTask.execute({
            db?.serverDao()?.delete()
        })
    }
}