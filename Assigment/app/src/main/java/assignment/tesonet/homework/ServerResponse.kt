package assignment.tesonet.homework

import android.support.annotation.IntDef
import assignment.tesonet.homework.ServerResponse.ERROR
import assignment.tesonet.homework.ServerResponse.NO_INTERNET
import assignment.tesonet.homework.ServerResponse.SUCCESS

@IntDef(SUCCESS, NO_INTERNET, ERROR)
@Retention(AnnotationRetention.SOURCE)
annotation class ServerResponseValue

object ServerResponse {
    const val SUCCESS = 0
    const val NO_INTERNET = 1
    const val ERROR = 2
}

