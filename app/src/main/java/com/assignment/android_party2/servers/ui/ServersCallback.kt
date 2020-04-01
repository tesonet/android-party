package com.assignment.android_party2.servers.ui

interface ServersCallback {
    fun onStarted()
    fun onFailure(errorMessage: String)
}