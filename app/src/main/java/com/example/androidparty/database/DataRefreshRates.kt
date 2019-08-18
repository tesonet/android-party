package com.example.androidparty.database

enum class DataRefreshRates(val ID: String, val renewTimeMS: Long) {
    Server("Server", 300000)
}