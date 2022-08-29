package com.example.excompose.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.excompose.R
import com.example.excompose.presentation.navigation.AppGraph

sealed class BottomBarItemConfig(
    val router: String,
    @StringRes val title: Int,
    val icon: ImageVector
) {
    object Home : BottomBarItemConfig(
        router = AppGraph.home.HOME,
        title = R.string.home,
        icon = Icons.Default.Home
    )

    object Profile : BottomBarItemConfig(
        router = AppGraph.home.PROFILE,
        title = R.string.profile,
        icon = Icons.Default.Person
    )

    object Settings : BottomBarItemConfig(
        router = AppGraph.home.SETTINGS,
        title = R.string.settings,
        icon = Icons.Default.Settings
    )
}

object BottomBarConfigList {
    val all = listOf(
        BottomBarItemConfig.Home,
        BottomBarItemConfig.Profile,
        BottomBarItemConfig.Settings,
    )
}

@Composable
fun BottomBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showNavigationItems =
        BottomBarConfigList.all.any { it.router == currentDestination?.route }
    if (showNavigationItems) {
        BottomNavigation {
            BottomBarConfigList.all.forEach { itemConfig ->
                AddItem(
                    itemConfig = itemConfig,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    itemConfig: BottomBarItemConfig,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = stringResource(itemConfig.title))
        },
        icon = {
            Icon(
                imageVector = itemConfig.icon,
                contentDescription = "Navigation Icon " + stringResource(itemConfig.title)
            )
        },
        selected = currentDestination?.hierarchy?.any { it.route == itemConfig.router } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(itemConfig.router) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )

}
