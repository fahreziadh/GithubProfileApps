package com.fahreziadha.githubprofile.ui.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(navController: NavHostController, viewModel: DetailViewModel = hiltViewModel()) {
    Text(text = "Detail")
}