package com.example.excompose.presentation.ui.view

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excompose.presentation.navigation.HomeNavGraph
import com.example.excompose.presentation.ui.components.BottomBar
import com.example.excompose.presentation.ui.components.FavoriteButton
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme
import com.example.excompose.presentation.ui.theme.ArsenalThemeExtended

@Composable
fun ViewContent(name: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier
                .clickable { onClick() }
                .animateContentSize(),
            text = name,
            fontSize = ArsenalThemeExtended.typography.h1.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun ViewContentPreview() {
    ArsenalBasicTheme(useDarkTheme = false) {
        ViewContent("Conteudo que exibirei!") {}
    }
}

@Preview
@Composable
fun HomeViewContentPreview() {
    ArsenalBasicTheme(useDarkTheme = false) {
        HomeViewContent(rememberNavController())
    }
}

@Composable
fun LoginContent(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgotClick: () -> Unit,
    onWelcomeClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FavoriteButton(
            text = "Login"
        ) { onLoginClick() }

        Text(
            modifier = Modifier
                .clickable { onSignUpClick() }
                .padding(40.dp),
            text = "SignUp",
            fontSize = ArsenalThemeExtended.typography.h1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .clickable { onForgotClick() }
                .padding(40.dp),
            text = "Forgot",
            fontSize = ArsenalThemeExtended.typography.h1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .clickable { onWelcomeClick() }
                .padding(40.dp),
            text = "Welcome",
            fontSize = ArsenalThemeExtended.typography.h1.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun LoginContentPreview() {
    ArsenalBasicTheme(useDarkTheme = true) {
        LoginContent({}, {}, {}, {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeViewContent(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        HomeNavGraph(navController = navController)
    }
}