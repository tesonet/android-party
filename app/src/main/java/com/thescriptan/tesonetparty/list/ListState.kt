package com.thescriptan.tesonetparty.list

sealed class ListState {
    object Authorized: ListState()
    object Logout: ListState()
}
