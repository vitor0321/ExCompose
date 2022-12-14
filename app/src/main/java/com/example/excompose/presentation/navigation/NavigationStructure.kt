package com.example.excompose.presentation.navigation

object AuthGraph{
    const val ROOT = "auth_graph"
    const val LOGIN = "login"
    const val SIGN_UP = "sign_up"
    const val FORGOT_PASSWORD = "forgot_password"
    const val WELCOME = "welcome"
}

object DetailsGraph{
    const val ROOT = "details_graph"
    const val HELP = "help"
    const val FAQ = "faq"
    const val DISCLAIMER = "disclaimer"
}

object HomeGraph{
    const val ROOT = "home_graph"
    const val HOME = "home"
    const val PROFILE = "profile"
    const val SETTINGS = "settings"
}

object RootGraph {
    const val ROOT = "root_graph"
}

object AppGraph{
    val initial = RootGraph
    val auth = AuthGraph
    val home = HomeGraph
    val details = DetailsGraph
}