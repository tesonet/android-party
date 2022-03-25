package com.example.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiEvent : ViewEvent, UiState : ViewState, UiEffect : ViewEffect> : ViewModel() {

    private val initialState: UiState by lazy { provideInitialState() }
    private val _viewState: MutableStateFlow<UiState> by lazy { MutableStateFlow(initialState) }
    val viewState: StateFlow<UiState> by lazy { _viewState }

    // Effect (side effects like error messages which we want to show only once)
    private val _effect: Channel<UiEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    // Event (user actions)
    private val _event: MutableSharedFlow<UiEvent> = MutableSharedFlow()

    init {
        viewModelScope.launch {
            _event.collect {
                handleEvent(it)
            }
        }
    }

    abstract fun provideInitialState(): UiState

    protected fun updateState(reducer: UiState.() -> UiState) {
        val newState = viewState.value.reducer()
        _viewState.value = newState
    }

    fun onUiEvent(event: UiEvent) {
        viewModelScope.launch { _event.emit(event) }
    }

    abstract fun handleEvent(event: UiEvent)

    protected fun sendEffect(effectBuilder: () -> UiEffect) {
        viewModelScope.launch { _effect.send(effectBuilder()) }
    }
}

interface ViewState

interface ViewEvent

interface ViewEffect
