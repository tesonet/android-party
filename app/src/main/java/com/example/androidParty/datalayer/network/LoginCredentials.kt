package com.example.androidParty.datalayer.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginCredentials(
  @Json(name = "username")
  val userName: String,
  @Json(name = "password")
  val password: String
)