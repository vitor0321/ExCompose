package com.example.excompose.presentation.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.excompose.R
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme

@Composable
fun BottomButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {}
) {
    Button(modifier = modifier, onClick = onClick) {
        Text(text = text)
    }
}

@Composable
fun TopButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {}
) {
    Button(modifier = modifier, onClick = onClick) {
        Text(text = text)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DialogButtonsPreview() {
    ArsenalBasicTheme {
        Column {
            TopButton(text = "Botão Superior" )
            Spacer(modifier = Modifier.height(12.dp))
            BottomButton(text = "Botão inferior")
        }
    }
}