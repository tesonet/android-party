package com.thescriptan.tesonetparty.list

sealed class ListState {
    object Authorized: ListState()
    object Fetching: ListState()
    object Logout: ListState()
}
