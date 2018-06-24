package com.ne2rnas.party.data.servers

import com.squareup.moshi.Json

data class Server(@Json(name = "name") val name: String,
                  @Json(name = "distance") val distance: Int)
