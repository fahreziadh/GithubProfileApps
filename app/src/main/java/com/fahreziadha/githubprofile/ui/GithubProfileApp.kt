package com.fahreziadha.githubprofile.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.fahreziadha.githubprofile.ui.utils.CustomSurface

@Composable
fun GithubProfileApp(modifier: Modifier) {
    val navController = rememberNavController()

    CustomSurface(color = GithubProfileTheme.colors.uiBackground) {
        Navigation(
            modifier = modifier,
            navController = navController
        )
    }

}