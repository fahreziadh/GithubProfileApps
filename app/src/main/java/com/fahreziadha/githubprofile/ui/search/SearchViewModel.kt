package com.fahreziadha.githubprofile.ui.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahreziadha.githubprofile.common.Resource
import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseItemDTO
import com.fahreziadha.githubprofile.domain.model.CacheUser
import com.fahreziadha.githubprofile.domain.model.User
import com.fahreziadha.githubprofile.domain.use_case.get_users.GetUsersUseCase
import com.fahreziadha.githubprofile.domain.use_case.local.LocalUseCase
import com.fahreziadha.githubprofile.ui.search.state.SearchHistoryResultState
import com.fahreziadha.githubprofile.ui.search.state.SearchScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val localUseCase: LocalUseCase
) : ViewModel() {

    val isRefreshing = MutableStateFlow(false)
    val pageCount = MutableStateFlow(1)
    val text = MutableStateFlow("")
    val result = text.debounce(500)
        .distinctUntilChanged()
        .onEach {
            if (it.isNotEmpty()) {
                getUserByName()
            } else {
                getCacheSearch()
                _screenState.value = SearchScreenState(isLoading = false)
            }
        }

    private var getLocaCachelJob: Job? = null


    private val _screenState = mutableStateOf(SearchScreenState(res = mutableListOf()))
    val screenState: State<SearchScreenState> = _screenState


    private val _cacheState = mutableStateOf(SearchHistoryResultState(res = mutableListOf()))
    val cacheState: State<SearchHistoryResultState> = _cacheState


    fun getUserByName(isLoadMore: Boolean = false) {
        if (isLoadMore) {
            pageCount.value++
        } else {
            pageCount.value = 1
        }
        if (text.value != "") {
            var list = _screenState.value.res
            getUsersUseCase(text.value, pageCount.value).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            if (isLoadMore) { //Check if is  from load more then add result the list
                                list = _screenState.value.res + it
                            } else {
                                list = it
                            }
                            _screenState.value = SearchScreenState(
                                isLoading = false,
                                res = list,
                                if (list.isEmpty()) "isEmpty" else ""
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
            //if user didnt input anything
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

    private fun getCacheSearch() {
        getLocaCachelJob?.cancel()
        getLocaCachelJob = localUseCase.getListSearch().onEach { result ->
            _cacheState.value = SearchHistoryResultState(
                isLoading = false,
                res = result
            )
        }.launchIn(viewModelScope)
    }

    fun saveCacheSearch(res: CacheUser) {
        viewModelScope.launch {
            localUseCase.addSearch(res)
        }
    }

    fun clearCacheSearch() {
        viewModelScope.launch {
            localUseCase.deleteSearch()
        }
    }
}