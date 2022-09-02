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
import com.example.excompose.presentation.ui.components.Item
import com.example.excompose.presentation.ui.components.SettingsScreen
import com.example.excompose.presentation.ui.components.SettingsScreenAnimated
import com.example.excompose.presentation.ui.components.SettingsViewModel
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
                //WelcomeView(this, viewModel)
                //ComplexNavigationGraph()
                //StateView(saveableViewModel)
//                LazyColumnScreen()
                LazyColumnAnimatedScreen()
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

@Composable
private fun LazyColumnScreen(){
    ArsenalBasicTheme {
        val viewModel = SettingsViewModel().apply {
            items.value = mutableListOf(
                Item(1,"meu item 1", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(2,"meu item 2", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(3,"meu item 3", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(4,"meu item 4", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(5,"meu item 5", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(6,"meu item 6", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(7,"meu item 7", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(8,"meu item 8", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(9,"meu item 9", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(10,"meu item 10", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(11,"meu item 11", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(12,"meu item 12", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(13,"meu item 13", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(14,"meu item 14", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(15,"meu item 15", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
            )
            registerId.value = "R1234566"
        }
        SettingsScreen(viewModel)
    }
}

@Composable
private fun LazyColumnAnimatedScreen(){
    ArsenalBasicTheme {
        val viewModel = SettingsViewModel().apply {
            items.value = mutableListOf(
                Item(1,"meu item 1", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(2,"meu item 2", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(3,"meu item 3", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(4,"meu item 4", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(5,"meu item 5", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(6,"meu item 6", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(7,"meu item 7", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(8,"meu item 8", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(9,"meu item 9", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(10,"meu item 10", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(11,"meu item 11", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(12,"meu item 12", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(13,"meu item 13", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(14,"meu item 14", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(15,"meu item 15", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
            )
            registerId.value = "R1234566"
        }
        SettingsScreenAnimated(viewModel)
    }
}