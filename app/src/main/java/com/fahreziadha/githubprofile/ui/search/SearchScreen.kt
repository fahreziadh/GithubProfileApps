package com.fahreziadha.githubprofile.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.fahreziadha.githubprofile.utils.CustomSurface
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun SearchScreen(
    state: SearchState = rememberSearchState(),
    viewModel: SearchViewModel = hiltViewModel()
) {

    CustomSurface(modifier = Modifier.fillMaxSize()) {
        Column {
            Spacer(modifier = Modifier.statusBarsPadding())
            SearchBar(
                query = state.query,
                onQueryChange = { state.query = it },
                searchFocused = state.focused,
                onSearchFocusChange = { state.focused = it },
                onClearQuery = { state.query = TextFieldValue("") },
                searching = state.searching
            )
            val state = viewModel.state.value

            Text(text = state)
            SearchResult(itemCount = 1500)
        }
    }
}


@Composable
private fun rememberSearchState(
    query: TextFieldValue = TextFieldValue(""),
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
    query: TextFieldValue,
    focused: Boolean,
    searching: Boolean,
) {
    var query by mutableStateOf(query)
    var focused by mutableStateOf(focused)
    var searching by mutableStateOf(searching)
}
