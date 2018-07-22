package com.amaximan.tesonet.model.database.server

import android.arch.persistence.room.Entity
import com.amaximan.tesonet.model.database.base.BaseEntity

@Entity(tableName = "Server")
class Server(val name: String?, val distance: String?) : BaseEntity()