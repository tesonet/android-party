package place.holder.androidparty.servers

import android.content.Context
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select
import place.holder.androidparty.common.database.DatabaseOpenHelper
import place.holder.androidparty.common.database.database
import place.holder.androidparty.servers.model.Server

class ServersProvider(private val context: Context) {

    fun getServers(): List<Server> {
        var serverList: List<Server> = emptyList()
        context.database.use {
            select(DatabaseOpenHelper.TABLE_SERVER,
                    DatabaseOpenHelper.SERVER_NAME,
                    DatabaseOpenHelper.SERVER_DISTANCE).exec {
                serverList = parseList(classParser())
            }
        }
        return serverList
    }
}