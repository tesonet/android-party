package com.giedrius.androidparty.task.viewmodel

data class ServerViewModel(
    val name: String,
    val distance: Int,
    val distanceUnits: String = "km"
)