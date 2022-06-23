package com.fahreziadha.githubprofile.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.fahreziadha.githubprofile.utils.CustomSurface
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest

@Composable
fun SearchScreen(
    state: SearchState = rememberSearchState(),
    viewModel: SearchViewModel = hiltViewModel()
) {
    val text by viewModel.text.collectAsState()
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
                searching = viewModel.screenState.value.isLoading
            )
            val data = viewModel.screenState.value
            data.res?.let { SearchResult(item = it) }
        }
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
    var searching by mutableStateOf(searching)
}
