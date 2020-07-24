package gj.tesonet.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class Server(@PrimaryKey(autoGenerate = true)
                  @ColumnInfo var id: Long = 0,
                  @NotNull @ColumnInfo val name: String,
                  @NotNull @ColumnInfo val distance: Int) {

    constructor() : this(0L, "", 0)

}
