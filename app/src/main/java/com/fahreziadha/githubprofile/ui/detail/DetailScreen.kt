package com.fahreziadha.githubprofile.ui.detail

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(viewModel: DetailViewModel = hiltViewModel()) {
    val state = viewModel.state.value

    Row() {
        state.user?.let {
            Text(text = it.toString())
        }
    }
}