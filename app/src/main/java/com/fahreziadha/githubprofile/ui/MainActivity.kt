package com.fahreziadha.githubprofile.ui

import GithubProfileTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fahreziadha.githubprofile.ui.detail.DetailScreen
import com.fahreziadha.githubprofile.ui.search.SearchScreen
import com.fahreziadha.githubprofile.utils.CustomSurface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubProfileTheme() {
                CustomSurface(color = GithubProfileTheme.colors.uiBackground) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SearchScreen.route
                    ) {
                        composable(route = Screen.SearchScreen.route) {
                            SearchScreen(navController = navController)
                        }
                        composable(route = Screen.DetailScreen.route+"/{id}") {
                            DetailScreen()
                        }
                    }
                }
            }
        }
    }
}