package assignment.tesonet.homework.api.response

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Server(@Expose val name: String, @Expose val distance: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}