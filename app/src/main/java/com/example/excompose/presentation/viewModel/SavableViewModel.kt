package com.example.excompose.presentation.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.excompose.presentation.viewModel.saveState.SaveableComposeState

class SavableViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var saveableMutableComposeState by SaveableComposeState(savedStateHandle, "ComposeKey", false)
        private set

    fun triggerComposeState(newState: Boolean) {
        saveableMutableComposeState = newState
    }
}