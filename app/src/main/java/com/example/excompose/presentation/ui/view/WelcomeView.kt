package com.example.excompose.presentation.ui.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.excompose.R
import com.example.excompose.presentation.ui.components.ArsenalButtonRow
import com.example.excompose.presentation.ui.components.ArsenalIconImage
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme
import com.example.excompose.presentation.ui.theme.ArsenalThemeExtended
import com.example.excompose.presentation.viewModel.ObserveStateViewModel

@Composable
fun WelcomeView(
    context: Context,
    viewModel: ObserveStateViewModel,
) {
    val action = { Toast.makeText(context, "Olha isso!", Toast.LENGTH_SHORT).show() }
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.background(MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier
                .weight(weight = 1f)
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Compose Exemplo",
                style = ArsenalThemeExtended.typography.h1,
            )

            Text(
                text = "Seja bem vindo ao Compose",
                style = ArsenalThemeExtended.typography.body1,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            ArsenalIconImage(R.drawable.ic_arsenal_plane)
        }
        LoadingView(viewModel)
        ArsenalButtonRow(
            modifier = Modifier.widthIn(min = 100.dp, max = 120.dp),
            positiveAction = action,
            positiveTextId = R.string.ok,
            neutralAction = action,
            neutralTextId = R.string.maybe,
            negativeAction = action,
            negativeTextId = R.string.cancel
        )
    }
}

@Preview
@Composable
fun WelcomeViewLightPreview() {
    ArsenalBasicTheme(useDarkTheme = false) {
        WelcomeView(LocalContext.current, ObserveStateViewModel().apply {
            loadingStateFlow.value = false
        })
    }
}

@Preview
@Composable
fun WelcomeViewDarkPreview() {
    ArsenalBasicTheme(useDarkTheme = true) {
        WelcomeView(LocalContext.current, ObserveStateViewModel().apply {
            loadingStateFlow.value = true
        })
    }
}