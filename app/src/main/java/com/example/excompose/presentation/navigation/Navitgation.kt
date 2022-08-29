package com.example.excompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.excompose.presentation.ui.theme.ArsenalBasicTheme

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = AppGraph.initial.ROOT,
        startDestination = AppGraph.auth.ROOT
    ) {
        authNavGraph(navController = navController)
        composable(route = AppGraph.home.ROOT) {
            HomeViewContent()
        }
    }
}

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = AppGraph.auth.ROOT,
        startDestination = AppGraph.auth.LOGIN
    ) {
        composable(route = AppGraph.auth.LOGIN) {
            ArsenalBasicTheme {
                LoginContent(
                    onLoginClick = {
                        navController.popBackStack()
                        navController.navigate(AppGraph.home.ROOT)
                    },
                    onSignUpClick = {
                        navController.navigate(AppGraph.auth.SIGN_UP)
                    },
                    onForgotClick = {
                        navController.navigate(AppGraph.auth.FORGOT_PASSWORD)
                    }
                )
            }
        }
        composable(route = AppGraph.auth.SIGN_UP) {
            ViewContent(name = "SIGN UP") {}
        }
        composable(route = AppGraph.auth.FORGOT_PASSWORD) {
            ViewContent(name = "FORGOT PASSWORD") {}
        }
    }
}

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = AppGraph.home.ROOT,
        startDestination = AppGraph.home.HOME
    ) {
        composable(route = AppGraph.home.HOME) {
            ViewContent(
                name = "Home",
                onClick = { navController.navigate(AppGraph.details.ROOT) }
            )
        }
        composable(route = AppGraph.home.PROFILE) {
            ViewContent(
                name = "Profile",
                onClick = { }
            )
        }
        composable(route = AppGraph.home.SETTINGS) {
            ViewContent(
                name = "Settings",
                onClick = { }
            )
        }
        detailsNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = AppGraph.details.ROOT,
        startDestination = AppGraph.details.HELP
    ) {
        composable(route = AppGraph.details.HELP) {
            ViewContent(name = "Help") {
                navController.navigate(AppGraph.details.DISCLAIMER)
            }
        }
        composable(route = AppGraph.details.DISCLAIMER) {
            ViewContent(name = "Disclaimer") {
                navController.navigate(AppGraph.details.FAQ)
            }
        }
        composable(route = AppGraph.details.FAQ) {
            ViewContent(name = "FAG") {
                navController.popBackStack(
                    route = AppGraph.details.FAQ,
                    inclusive = false
                )
            }
        }
    }
}