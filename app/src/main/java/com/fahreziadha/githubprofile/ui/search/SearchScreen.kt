package com.fahreziadha.githubprofile.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.fahreziadha.githubprofile.R
import com.fahreziadha.githubprofile.utils.CustomSurface
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun SearchScreen(
    navController: NavController,
    state: SearchState = rememberSearchState(),
    viewModel: SearchViewModel = hiltViewModel()
) {
    val text by viewModel.text.collectAsState()
    val pageCount by viewModel.pageCount.collectAsState()
    val result by viewModel.result.collectAsState(initial = "")

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
            if (viewModel.screenState.value.isLoading && pageCount == 1) {
                Loader(R.raw.loading)
            }
            viewModel.screenState.value.res?.let {
                if (it.isEmpty() && text != "" && viewModel.screenState.value.error == "isEmpty") {
                    Loader(R.raw.not_found)
                }
                SearchResult(item = it,navController=navController)
            }
        }
    }
}

@Composable
private fun Loader(loading: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(loading))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        composition,
        progress,
    )
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
