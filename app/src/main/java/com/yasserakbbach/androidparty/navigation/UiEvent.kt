package com.yasserakbbach.androidparty.navigation

sealed class UiEvent {
    data class NavigateTo(val screen: Screen) : UiEvent()
}
