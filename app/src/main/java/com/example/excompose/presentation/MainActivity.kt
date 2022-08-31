package com.example.excompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.excompose.presentation.navigation.RootNavigationGraph
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme
import com.example.excompose.presentation.ui.view.WelcomeView
import com.example.excompose.presentation.viewModel.ObserveStateViewModel
import com.example.excompose.presentation.viewModel.SavableViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val saveableViewModel: SavableViewModel by viewModels()
        val viewModel = ObserveStateViewModel()
        viewModel.setLoadingState(true)

        setContent {
            ArsenalBasicTheme {
//                WelcomeView(this, viewModel)
                ComplexNavigationGraph()
                //StateView(saveableViewModel)
            }
        }
    }
}

@Preview
@Composable
fun MainPreview() {
    ArsenalBasicTheme {
        ComplexNavigationGraph()
    }
}

@Composable
private fun ComplexNavigationGraph() {
    ArsenalBasicTheme {
        RootNavigationGraph(navController = rememberNavController())
    }
}