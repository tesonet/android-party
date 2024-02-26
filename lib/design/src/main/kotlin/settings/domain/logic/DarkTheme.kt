package settings.domain.logic

import android.annotation.SuppressLint
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.Q
import androidx.annotation.StringRes
import com.github.k4dima.testio.lib.design.R as LibDesignR

enum class DarkTheme(@StringRes val id: Int) {
    System(LibDesignR.string.system_theme), Off(LibDesignR.string.light), On(LibDesignR.string.dark);

    companion object {
        @SuppressLint("AnnotateVersionCheck")
        private val android10 = SDK_INT >= Q
        val entriesCompat = entries
            .let { if (!android10) it.toMutableList().apply { remove(System) } else it }
        val defaultIndex = (if (android10) System else Off).let { entriesCompat.indexOf(it) }
        fun index(darkTheme: DarkTheme) = entriesCompat.indexOf(darkTheme)
    }
}