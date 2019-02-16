package lt.petraslabutis.testio.extensions

import com.squareup.moshi.Moshi
import lt.petraslabutis.testio.exceptions.ApiException
import retrofit2.HttpException
import java.io.IOException

fun Throwable.asApiException(): ApiException? {
    try {
        (this as? HttpException)?.let {
            return Moshi.Builder()
                .build()
                .adapter(ApiException::class.java)
                .fromJson(response().errorBody()?.string() ?: "")
        }
    } catch (exception: IOException) {
        return null
    }
    return null
}