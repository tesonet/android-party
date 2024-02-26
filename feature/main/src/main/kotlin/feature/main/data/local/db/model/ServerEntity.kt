package feature.main.data.local.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "servers")
class ServerEntity(val name: String, val distance: Int) {
    @PrimaryKey(autoGenerate = true)
    var pk = 0L
}