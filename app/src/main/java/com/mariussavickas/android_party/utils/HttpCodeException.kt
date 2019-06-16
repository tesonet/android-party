package com.codecave.outmatch.shared.exceptions

import java.net.HttpURLConnection

class HttpCodeException() : Exception() {
    var code = HttpURLConnection.HTTP_OK

    constructor(code: Int) : this() {
        this.code = code
    }
}