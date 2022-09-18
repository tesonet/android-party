package com.ac.androidparty.core.components.loading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoadingComponentViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(true)
    val state: StateFlow<Boolean>
        get() = _state

    fun load() {
        _state.value = true
        viewModelScope.launch {
            flowOf(dismissAnimation()).collect { isLoading ->
                _state.value = isLoading
            }
        }
    }

    private suspend fun dismissAnimation(): Boolean {
        delay(1500)
        return false
    }
}