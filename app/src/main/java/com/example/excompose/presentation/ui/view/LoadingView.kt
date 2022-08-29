package com.example.excompose.presentation.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.excompose.R
import com.example.excompose.presentation.ui.components.ArsenalButton
import com.example.excompose.presentation.ui.components.ArsenalCircularProgressIndicator
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme
import com.example.excompose.presentation.ui.theme.ArsenalThemeExtended
import com.example.excompose.presentation.viewModel.ObserveStateViewModel

@Composable
fun LoadingView(viewModel: ObserveStateViewModel) {

    val loading by viewModel.loadingStateFlow.collectAsState()

    when {
        loading -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ArsenalCircularProgressIndicator(Modifier, R.string.loading)
                ArsenalButton(
                    modifier = Modifier,
                    clickAction = {viewModel.setLoadingState(false)},
                    textId =R.string.exit_loading
                )
            }
        }
        else -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Aeeeeee! Consegui!!!",
                    style = ArsenalThemeExtended.typography.h1
                )
                ArsenalButton(
                    modifier = Modifier,
                    clickAction = {viewModel.setLoadingState(false)},
                    textId =R.string.exit_loading
                )
            }
        }
    }
}

@Preview
@Composable
fun LoadingViewLightPreview(){
    ArsenalBasicTheme(useDarkTheme = false) {
        LoadingView(ObserveStateViewModel().apply {
            loadingStateFlow.value = true
        })
    }
}

@Preview
@Composable
fun LoadingViewDarkPreview(){
    ArsenalBasicTheme(useDarkTheme = true) {
        LoadingView(ObserveStateViewModel().apply {
            loadingStateFlow.value = false
        })
    }
}