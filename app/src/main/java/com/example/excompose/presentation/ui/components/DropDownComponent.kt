package com.example.excompose.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme

@Composable
fun DropDownComponent(
    title: String,
    description: String,
    minimizeText: String,
    maximizeText: String,
    contentDescription: String,
    expanded: Boolean,
    expandAction: () -> Unit
) {
    Row(
        modifier = Modifier.padding(top = 0.dp, start = 24.dp, end = 24.dp, bottom = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 14.dp)
        ) {
            Text(text = title, fontWeight = FontWeight.Bold)
            if (expanded) {
                Text(text = description)
            }
        }
        TextButton(onClick = expandAction) {
            Icon(
                if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = contentDescription
            )
            Text(
                text = if (expanded) minimizeText else maximizeText,
                fontSize = 12.sp
            )
        }
    }
}

@Preview
@Composable
fun DropDownComponentLightPreview() {
    ArsenalBasicTheme(useDarkTheme = false) {
        DropDownComponent(
            title = "Title",
            description = "description",
            minimizeText = "minimize",
            maximizeText = "maximize",
            contentDescription = "description",
            expanded = false,
        ) {}
    }
}

@Preview
@Composable
fun DropDownComponentDarkPreview() {
    ArsenalBasicTheme(useDarkTheme = true) {
        DropDownComponent(
            title = "Title",
            description = "description",
            minimizeText = "minimize",
            maximizeText = "maximize",
            contentDescription = "description",
            expanded = true,
        ) {}
    }
}