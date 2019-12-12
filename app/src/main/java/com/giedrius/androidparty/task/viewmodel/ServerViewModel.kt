package com.giedrius.androidparty.task.viewmodel

data class ServerViewModel(
    var name: String,
    var distance: Int,
    var distanceUnits: String = "km"
)