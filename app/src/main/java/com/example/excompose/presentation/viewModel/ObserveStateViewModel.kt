package com.example.excompose.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ObserveStateViewModel : ViewModel() {

    val loadingStateFlow = MutableStateFlow(false)

    fun setLoadingState(loading: Boolean) {
        viewModelScope.launch {
            loadingStateFlow.emit(loading)
        }
    }
}