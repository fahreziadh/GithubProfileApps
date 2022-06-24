package com.fahreziadha.githubprofile.main.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.fahreziadha.githubprofile.main.model.CacheUser
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

    val isRefreshing = MutableStateFlow(false)
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

        if (isLoadMore) pageCount.value++
        else pageCount.value = 1

        _uiState.value = SearchScreenState.Loading

        loadingJob = viewModelScope.launch {
            val usersResult = getUsersUseCase(query = query, page = pageCount.value)
            _uiState.value = (SearchScreenState.Success(usersResult))
        }
    }

    fun insertCacheUser(cacheUser: CacheUser) {
        viewModelScope.launch {
            insertCacheUserUseCase(cacheUser = cacheUser)
        }
    }

    val uiStateCacheUser: LiveData<List<CacheUser>> = getCacheUsersUseCase()

    fun clearUserCache() {
        viewModelScope.launch {
            deleteCacheUserUseCase()
        }
    }

    fun refresh() {
        getUsersByQuery()
    }
}