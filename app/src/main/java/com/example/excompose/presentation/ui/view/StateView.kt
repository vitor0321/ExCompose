package com.example.excompose.presentation.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import com.example.excompose.presentation.ui.components.ExpandableSavableView
import com.example.excompose.presentation.ui.components.ExpandableSavableViewHoisted
import com.example.excompose.presentation.ui.components.ExpandableSavableViewHoistedSavable
import com.example.excompose.presentation.ui.components.ExpandableView
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme
import com.example.excompose.presentation.viewModel.ExpandableViewModel
import com.example.excompose.presentation.viewModel.SavableViewModel

@Composable
fun StateView(viewModel: SavableViewModel) {
    ArsenalBasicTheme {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "COM STATES",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
            )
            ExpandableView()
            ExpandableSavableView()
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = "COM VIEW MODELS",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
            )
            ExpandableSavableViewHoisted(ExpandableViewModel())
            ExpandableSavableViewHoistedSavable(viewModel)
        }
    }
}

@Preview
@Composable
fun StateViewLightPreview() {
    val savedStateHandle = SavedStateHandle()
    val saveableViewModel = SavableViewModel(savedStateHandle)

    ArsenalBasicTheme(useDarkTheme = false) {
        StateView(saveableViewModel)
    }
}

@Preview
@Composable
fun StateViewDarkPreview() {
    val savedStateHandle = SavedStateHandle()
    val saveableViewModel = SavableViewModel(savedStateHandle)

    ArsenalBasicTheme(useDarkTheme = true) {
        StateView(saveableViewModel)
    }
}