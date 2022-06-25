package com.fahreziadha.githubprofile.main.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.fahreziadha.githubprofile.main.model.CacheUser
import com.fahreziadha.githubprofile.main.model.User
import com.fahreziadha.githubprofile.main.usecase.cache.DeleteCacheUserUseCase
import com.fahreziadha.githubprofile.main.usecase.cache.GetCacheUsersUseCase
import com.fahreziadha.githubprofile.main.usecase.cache.InsertCacheUserUseCase
import com.fahreziadha.githubprofile.main.usecase.getusers.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    getCacheUsersUseCase: GetCacheUsersUseCase,
    private val getUsersUseCase: GetUsersUseCase,
    private val insertCacheUserUseCase: InsertCacheUserUseCase,
    private val deleteCacheUserUseCase: DeleteCacheUserUseCase
) : ViewModel() {

    val pageCount = MutableStateFlow(1)
    val text = MutableStateFlow("")

    val result = text.debounce(500)
        .distinctUntilChanged()
        .onEach {
            loadingJob?.cancel()
            if (it.isNotEmpty()) {
                getUsersByQuery()
            } else {
                _uiState.value = SearchScreenState.Idle(emptyList())
            }
        }


    private var loadingJob: Job? = null

    private val _uiState = MutableStateFlow<SearchScreenState>(SearchScreenState.Idle(emptyList()))
    val uiState: StateFlow<SearchScreenState> = _uiState

    fun getUsersByQuery(query: String = text.value, isLoadMore: Boolean = false) {
        if (isLoadMore) {
            pageCount.value++
            _uiState.value = (SearchScreenState.Success(
                (_uiState.value as SearchScreenState.Success).res,
                isLoadMoreLoadingActive = true
            ))
        } else {
            _uiState.value = SearchScreenState.Loading
            pageCount.value = 1
        }


        loadingJob = viewModelScope.launch {
            val usersResult = getUsersUseCase(query = query, page = pageCount.value)
            _uiState.value =
                (SearchScreenState.Success(
                    if (!isLoadMore) usersResult else (_uiState.value as SearchScreenState.Success).res + usersResult,
                    isLoadMoreLoadingActive = false
                ))
        }
    }

    fun insertCacheUser(cacheUser: CacheUser) {
        viewModelScope.launch {
            insertCacheUserUseCase(cacheUser = cacheUser)
        }
    }

    val uiStateCacheUser: LiveData<SearchScreenState> = getCacheUsersUseCase().map { list ->
        if (list.isEmpty()) {
            SearchScreenState.Idle(emptyList())
        } else {
            SearchScreenState.Idle(list)
        }
    }

    fun clearUserCache() {
        viewModelScope.launch {
            deleteCacheUserUseCase()
        }
    }

    fun loadMore() {
        getUsersByQuery(isLoadMore = true)
    }

    fun refresh() {
        getUsersByQuery()
    }
}