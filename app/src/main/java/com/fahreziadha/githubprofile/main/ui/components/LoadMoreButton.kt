package com.fahreziadha.githubprofile.main.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fahreziadha.githubprofile.main.ui.search.SearchViewModel


@Composable
fun LoadMoreButton(viewModel: SearchViewModel = hiltViewModel()) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 14.dp, horizontal = 21.dp)
    ) {
//        Button(
//            onClick = { viewModel.getUserByName(isLoadMore = true) },
//            colors = ButtonDefaults.buttonColors(Color.White),
//            modifier = Modifier
//                .fillMaxWidth()
//                .size(50.dp), elevation = ButtonDefaults.elevation(
//                defaultElevation = 1.dp,
//                pressedElevation = 5.dp,
//                disabledElevation = 0.dp
//            )
//        ) {
//            if (viewModel.screenState.value.isLoading) {
//                CircularProgressIndicator(color = GithubProfileTheme.colors.textSecondary)
//            } else {
//                Text(text = "Load More")
//            }
//        }
    }
}
