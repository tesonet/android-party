import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class Version(major: Int = 0, minor: Int = 1, patch: Int = 0) {
    val code = major * 1_000_000 + minor * 1_000 + patch
    val name = "$major.$minor.$patch"

    companion object {
        val date: String
            get() = SimpleDateFormat("yyMMdd-HHmmss", Locale.US)
                .also { it.timeZone = TimeZone.getTimeZone("UTC") }
                .format(Date())
    }
}