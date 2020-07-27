package lt.havefun.tesonetfunparty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lt.havefun.tesonetfunparty.data.IPreferencesManager
import lt.havefun.tesonetfunparty.data.PreferencesManager
import lt.havefun.tesonetfunparty.data.db.CachedServersListRepository

class MainActivityViewModel(
    private val preferencesManager: IPreferencesManager,
    private val cachedServersListRepository: CachedServersListRepository
): ViewModel() {
    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val preferencesManager: IPreferencesManager,
        private val cachedServersListRepository: CachedServersListRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainActivityViewModel(
                preferencesManager = preferencesManager,
                cachedServersListRepository = cachedServersListRepository
            ) as T
        }
    }

    fun hasToken() = preferencesManager.getToken() != null

    fun logout() {
        preferencesManager.saveToken(null)
        cachedServersListRepository.deleteAllServers()
    }
}