package com.fahreziadha.githubprofile.main.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.fahreziadha.githubprofile.R
import com.fahreziadha.githubprofile.ui.utils.CustomSurface
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: SearchState = rememberSearchState(),
    viewModel: SearchViewModel = hiltViewModel()
) {
    val text by viewModel.text.collectAsState()
    val result by viewModel.result.collectAsState(initial = "")
    val uiState = viewModel.uiState.collectAsState().value

    CustomSurface(modifier = Modifier.fillMaxSize()) {
        Column {
            Spacer(modifier = Modifier.statusBarsPadding())
            SearchBar(
                query = text,
                onQueryChange = { viewModel.text.value = it },
                searchFocused = state.focused,
                onSearchFocusChange = { state.focused = it },
                onClearQuery = { state.query = "" },
                searching = false
            )
            when (uiState) {
                is SearchScreenState.Loading -> Loader(loading = R.raw.loading)
                is SearchScreenState.Error -> Loader(loading = R.raw.not_found)
                is SearchScreenState.Success -> {
                    SearchResult(item = uiState.res, navController = navController)
                }
                is SearchScreenState.Idle -> {
                    val cacheUiState = viewModel.uiStateCacheUser.observeAsState().value
                    cacheUiState?.let {
                        if (cacheUiState is SearchScreenState.Idle) {
                            SearchHistoryResult(
                                data = cacheUiState.res,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Loader(loading: Int, modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(loading))
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition,
            restartOnPlay = true,
            iterations = 5,
            alignment = Alignment.Center,
            modifier = modifier.size(120.dp)
        )
    }

}


@Composable
private fun rememberSearchState(
    query: String = "",
    focused: Boolean = false,
    searching: Boolean = false,
): SearchState {
    return remember {
        SearchState(
            query = query,
            focused = focused,
            searching = searching,
        )
    }
}

@Stable
class SearchState(
    query: String,
    focused: Boolean,
    searching: Boolean,
) {
    var query by mutableStateOf(query)
    var focused by mutableStateOf(focused)
}
