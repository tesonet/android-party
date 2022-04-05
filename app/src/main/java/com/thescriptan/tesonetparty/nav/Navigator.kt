package com.thescriptan.tesonetparty.nav

import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@ActivityScoped
class Navigator @Inject constructor() {
    private val _sharedFlow = MutableSharedFlow<Screen>(extraBufferCapacity = 1)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun navigateTo(screen: Screen) {
        _sharedFlow.tryEmit(screen)
    }
}