package com.fahreziadha.githubprofile.ui

sealed class Screen(val route: String) {
    object SearchScreen : Screen("seacrh_screen")
    object DetailScreen : Screen("deteail_screen")
}