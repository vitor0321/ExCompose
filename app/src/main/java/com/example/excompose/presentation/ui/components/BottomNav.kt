package com.example.excompose.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme

@Composable
fun BottomNav(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {

        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Tela de home"
                )
            },
            label = {
                Text(text = "Home")
            },
            selected = true,
            onClick = onHomeClick
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    //painter = painterResource(id = R.drawable.ic_droid)
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Tela de home"
                )
            },
            label = {
                Text(text = "Home")
            },
            selected = false,
            onClick = onProfileClick
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun BottomNavPreview(){
    ArsenalBasicTheme {
        Scaffold(
            bottomBar = { BottomNav()}
        ) { padding ->
            HomeContent(Modifier.padding(padding))
        }
    }
}