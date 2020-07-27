package lt.havefun.tesonetfunparty.data.db

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.runBlocking
import lt.havefun.tesonetfunparty.data.Server

class CachedServersListRepository(private val serversDao: ServersDao) {
    fun saveServers(servers: List<Server>) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                serversDao.insertAll(servers)
            }
        }
    }

    fun getCachedServers(): List<Server>? = runBlocking(Dispatchers.Default) {
        return@runBlocking withContext(Dispatchers.Default) {
            serversDao.getAll()
        }
    }

    fun deleteAllServers() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                serversDao.deleteAll()
            }
        }
    }
}