package com.android.greecekino.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.greecekino.contracts.UiEffect
import com.android.greecekino.contracts.UiEvent
import com.android.greecekino.contracts.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<Event : UiEvent, State : UiState, Effect : UiEffect> : ViewModel() {

    val ioContext: () -> CoroutineContext = { Dispatchers.IO }

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    val currentState: State
        get() = state.value

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _effect = MutableSharedFlow<UiEffect>()
    val effect = _effect.asSharedFlow()

    init {
        subscribeEvents()
    }

    /**
     * Start listening to Event
     */
    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }
    }


    /**
     * Handle each event
     */
    abstract fun handleEvent(event: Event)


    /**
     * Set new Event
     */
    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }


    /**
     * Set new Ui State
     */
    protected fun setState(newState: State) {
        _state.value = newState
    }

    /**
     * Set new Effect
     */
    protected fun setEffect(effect: Effect) {
        viewModelScope.launch { _effect.emit(effect) }
    }

}