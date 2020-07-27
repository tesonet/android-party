package lt.havefun.tesonetfunparty.servers

import lt.havefun.tesonetfunparty.data.Server
import lt.havefun.tesonetfunparty.data.db.ServersDao

class TestServerDao: ServersDao {
    override fun insertAll(servers: List<Server>) {
    }

    override fun getAll(): List<Server> {
        return emptyList()
    }

    override fun deleteAll() {
    }
}