package com.example.core.ext

import java.io.IOException
import java.net.UnknownHostException

fun Throwable.isNetworkException() =
    this is IOException || this is UnknownHostException
