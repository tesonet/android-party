package assignment.tesonet.homework.ui.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import assignment.tesonet.homework.App
import assignment.tesonet.homework.ui.viewmodel.ServerActivityViewModel

class ServerActivityModelFactory(private val app: App) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ServerActivityViewModel::class.java)) {
            return ServerActivityViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}