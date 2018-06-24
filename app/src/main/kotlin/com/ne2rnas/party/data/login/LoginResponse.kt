package com.ne2rnas.party.data.login

import com.squareup.moshi.Json

data class LoginResponse(@Json(name = "token") val token: String)
