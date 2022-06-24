package com.fahreziadha.githubprofile.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.fahreziadha.githubprofile.main.ui.detail.DetailScreen
import com.fahreziadha.githubprofile.main.ui.search.SearchScreen
import com.fahreziadha.githubprofile.model.Destination

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.Search.path
    ) {
        composable(Destination.Search.path) {
            SearchScreen(modifier = modifier, navController = navController)
        }
        composable(Destination.Detail.path+"/{id}") {
            DetailScreen(modifier = modifier)
        }
    }
}