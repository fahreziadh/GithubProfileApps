package com.fahreziadha.githubprofile.ui.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahreziadha.githubprofile.common.Resource
import com.fahreziadha.githubprofile.domain.use_case.get_users.GetUsersUseCase
import com.fahreziadha.githubprofile.ui.search.state.SearchScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    val isRefreshing = MutableStateFlow(false)
    val pageCount = MutableStateFlow(1)
    val text = MutableStateFlow("")
    val result = text.debounce(500)
        .distinctUntilChanged()
        .onEach {
            getUserByName()
        }

    private val _screenState = mutableStateOf(SearchScreenState(res = mutableListOf()))
    val screenState: State<SearchScreenState> = _screenState


    fun getUserByName(isLoadMore: Boolean = false) {
        if (isLoadMore) {
            pageCount.value++
        } else {
            pageCount.value = 1
        }
        if (text.value != "") {
            getUsersUseCase(text.value, pageCount.value).onEach { result ->
                when (result) {
                    is Resource.Success -> {

                        result.data?.let {
                            _screenState.value = SearchScreenState(
                                isLoading = false,
                                res = _screenState.value.res + it
                            )
                        }

                    }
                    is Resource.Error -> {
                        _screenState.value = SearchScreenState(
                            isLoading = false,
                            emptyList(),
                            result.message ?: "An unexpected error occured"
                        )
                    }
                    is Resource.Loading -> {
                        var list = _screenState.value.res
                        if (!isLoadMore) {
                            list = emptyList()
                        }
                        _screenState.value = SearchScreenState(
                            isLoading = true,
                            list,
                            ""
                        )
                    }
                }
            }.launchIn(viewModelScope)
        } else {
            _screenState.value = SearchScreenState(
                isLoading = false,
                emptyList(),
                ""
            )
        }
    }

    fun refresh() {
        _screenState.value = SearchScreenState(isLoading = true, res = emptyList())
        getUserByName()
    }
}