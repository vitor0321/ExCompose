package com.example.excompose.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExpandableViewModel : ViewModel() {

    private val _state = MutableStateFlow(false)
    val state: StateFlow<Boolean>
        get() = _state

    fun toggleState(newState: Boolean){
        viewModelScope.launch {
            _state.emit(newState)
        }
    }
}