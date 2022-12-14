package com.example.excompose.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.excompose.R
import com.example.excompose.presentation.navigation.RootNavigationGraph
import com.example.excompose.presentation.ui.FakeContent
import com.example.excompose.presentation.ui.components.BottomNav
import com.example.excompose.presentation.ui.components.Drawer
import com.example.excompose.presentation.ui.components.FullScreenMessageWithSaveable
import com.example.excompose.presentation.ui.components.FullScreenMessageWithState
import com.example.excompose.presentation.ui.components.HomeContent
import com.example.excompose.presentation.ui.components.Item
import com.example.excompose.presentation.ui.components.LandingScreen
import com.example.excompose.presentation.ui.components.ProfileImage
import com.example.excompose.presentation.ui.components.ProfileImageItem
import com.example.excompose.presentation.ui.components.ProfileRow
import com.example.excompose.presentation.ui.components.SearchViewModel
import com.example.excompose.presentation.ui.components.SearchableTopbar
import com.example.excompose.presentation.ui.components.SettingsScreen
import com.example.excompose.presentation.ui.components.SettingsScreenAnimated
import com.example.excompose.presentation.ui.components.SettingsViewModel
import com.example.excompose.presentation.ui.components.SimpleCard
import com.example.excompose.presentation.ui.components.SimpleCardItem
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme
import com.example.excompose.presentation.viewModel.ObserveStateViewModel
import com.example.excompose.presentation.viewModel.SavableViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ObserveStateViewModel()
        viewModel.setLoadingState(true)
        val saveableViewModel: SavableViewModel by viewModels()
        val searchViewModel: SearchViewModel by viewModels()

        setContent {
            ArsenalBasicTheme {
                //WelcomeView(this, viewModel)
                //ComplexNavigationGraph()
                //StateView(saveableViewModel)
                //LazyColumnScreen()
                //LazyColumnAnimatedScreen()
                //SearcheableTopBarScreen(searchViewModel)
                //ProfileImageScreen()
                //SimpleCardPreviewSreen()
                //BottomNavScreen()
                //SplashScreen()
//                DrawerScreen()
//                FullScreenMessage(title = "Titulo", message = "message", bottomButtonText ="Button" )
//                FullScreenMessageWithState(
//                    title = "titulo",
//                    message = "message",
//                    bottomButtonText = "bottom",
//                    bottomButtonAction = {
//                        Toast.makeText(this, "Salvar registro novamente", Toast.LENGTH_SHORT).show()
//                    }
//                )
                FullScreenMessageWithSaveable(
                    title = "titulo",
                    message = "message",
                    bottomButtonText = "bottom",
                    bottomButtonAction = {
                        Toast.makeText(this, "Salvar registro novamente", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun MainPreview() {
    ArsenalBasicTheme {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.onPrimary)
        ) {
            BottomNavScreen()
            SimpleCardPreviewSreen()
            ProfileImageScreenRow()
            ProfileImageScreen()
            //WelcomeView(this, viewModel)
            ComplexNavigationGraph()
            //StateView(saveableViewModel)
            LazyColumnScreen()
            LazyColumnAnimatedScreen()
            //SearcheableTopBarScreen(searchViewModel)
            FullScreenMessageWithState(
                title = "titulo",
                message = "message",
                bottomButtonText = "bottom"
            )
        }
    }
}

private fun showSelection(context: Context, selectionId: Int) {
    Toast.makeText(context, "Cliquei: $selectionId", Toast.LENGTH_SHORT).show()
}

@Composable
fun DrawerScreen() {
    ArsenalBasicTheme {
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier.statusBarsPadding(),
            drawerContent = {
                Drawer(onClick = ::showSelection)
            }
        ) { padding ->
            val scope = rememberCoroutineScope()
            FakeContent(
                modifier = Modifier.padding(padding),
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        }
    }
}

@Composable
fun SplashScreen() {
    ArsenalBasicTheme {
        Surface(
            color = MaterialTheme.colorScheme.primary
        ) {
            var showLandingScreen by remember { mutableStateOf(true) }
            if (showLandingScreen) {
                LandingScreen(
                    modifier = Modifier,
                    splashWaitTime = 1_500L,
                    onTimeOut = { showLandingScreen = false }
                )
            } else {
                BottomNavScreen()
            }
        }
    }
}

@Composable
fun BottomNavScreen() {
    ArsenalBasicTheme {
        Scaffold(
            bottomBar = { BottomNav() }
        ) { padding ->
            HomeContent(
                modifier = Modifier.padding(padding),
                rowData = listOf(
                    ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome),
                    ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome),
                    ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome),
                    ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome),
                    ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome),
                    ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome),
                    ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome),
                    ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome),
                ),
                gridViewData = listOf(
                    SimpleCardItem(R.drawable.marvel_logo, R.string.wellcome, 1),
                    SimpleCardItem(R.drawable.marvel_logo, R.string.wellcome, 2),
                    SimpleCardItem(R.drawable.marvel_logo, R.string.wellcome, 3),
                    SimpleCardItem(R.drawable.marvel_logo, R.string.wellcome, 4),
                    SimpleCardItem(R.drawable.marvel_logo, R.string.wellcome, 5),
                    SimpleCardItem(R.drawable.marvel_logo, R.string.wellcome, 6),
                    SimpleCardItem(R.drawable.marvel_logo, R.string.wellcome, 7),
                )
            )
        }
    }
}

@Composable
fun SimpleCardPreviewSreen() {
    ArsenalBasicTheme {
        SimpleCard(
            text = R.string.wellcome,
            drawable = R.drawable.marvel_logo,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ProfileImageScreenRow() {
    ArsenalBasicTheme {
        ProfileRow(
            rowData = listOf(
                ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome, 1),
                ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome, 2),
                ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome, 3),
                ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome, 4),
                ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome, 5),
                ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome, 6),
                ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome, 7),
                ProfileImageItem(R.drawable.marvel_logo, R.string.wellcome, 8),
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ProfileImageScreen() {
    ArsenalBasicTheme {
        ProfileImage(
            R.drawable.marvel_logo,
            R.string.wellcome,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun ComplexNavigationGraph() {
    ArsenalBasicTheme {
        RootNavigationGraph(navController = rememberNavController())
    }
}

@Composable
private fun LazyColumnScreen() {
    ArsenalBasicTheme {
        val viewModel = SettingsViewModel().apply {
            items.value = mutableListOf(
                Item(1, "meu item 1", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(2, "meu item 2", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(3, "meu item 3", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(4, "meu item 4", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(5, "meu item 5", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(6, "meu item 6", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(7, "meu item 7", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(8, "meu item 8", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(9, "meu item 9", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(10, "meu item 10", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(11, "meu item 11", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(12, "meu item 12", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(13, "meu item 13", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(14, "meu item 14", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(15, "meu item 15", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
            )
            registerId.value = "R1234566"
        }
        SettingsScreen(viewModel)
    }
}

@Composable
private fun LazyColumnAnimatedScreen() {
    ArsenalBasicTheme {
        val viewModel = SettingsViewModel().apply {
            items.value = mutableListOf(
                Item(1, "meu item 1", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(2, "meu item 2", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(3, "meu item 3", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(4, "meu item 4", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(5, "meu item 5", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(6, "meu item 6", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(7, "meu item 7", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(8, "meu item 8", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(9, "meu item 9", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(10, "meu item 10", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(11, "meu item 11", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(12, "meu item 12", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(13, "meu item 13", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(14, "meu item 14", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
                Item(15, "meu item 15", "Aug. 2022", "Sept. 2022", "AAAaAAA"),
            )
            registerId.value = "R1234566"
        }
        SettingsScreenAnimated(viewModel)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearcheableTopBarScreen(mainViewModel: SearchViewModel) {
    val isShowingSeachField by mainViewModel.isShowingSearchField
    val currentSearchText by mainViewModel.currentSearchText

    ArsenalBasicTheme {
        Scaffold(
            topBar = {
                SearchableTopbar(
                    isShowingSearchField = isShowingSeachField,
                    currentSearchText = currentSearchText,
                    onSearchTextChanged = { mainViewModel.setCurrentSearchText(newText = it) },
                    onSearchDeactivated = { mainViewModel.showSearchField(show = false) },
                    onSearchDispatched = { Log.d("SEARCH_TEST", "Usu??rio pesquisou por ") },
                    onSearchIconClicked = { mainViewModel.showSearchField(show = true) }
                )
            }
        ) {}
    }
}