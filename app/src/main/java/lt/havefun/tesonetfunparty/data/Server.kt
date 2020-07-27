package lt.havefun.tesonetfunparty.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import lt.havefun.tesonetfunparty.data.Server.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    indices = [
        Index(value = ["name"], unique = true)
    ]
)
data class Server(@PrimaryKey
                  val name: String,
                  val distance: Long) {

    companion object {
        const val TABLE_NAME = "servers"
    }
}

