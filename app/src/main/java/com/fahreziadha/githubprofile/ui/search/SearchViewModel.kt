package com.fahreziadha.githubprofile.ui.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahreziadha.githubprofile.common.Resource
import com.fahreziadha.githubprofile.data.remote.dto.UserResponseDTO
import com.fahreziadha.githubprofile.domain.use_case.get_user.GetUserUseCase
import com.fahreziadha.githubprofile.domain.use_case.get_users.GetUsersUseCase
import com.fahreziadha.githubprofile.ui.search.state.SearchScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    val text = MutableStateFlow("")
    val result = text.debounce(500)
        .distinctUntilChanged()
        .onEach {
            getUserByName(it)
        }

    private val _screenState = mutableStateOf(SearchScreenState(res = mutableListOf()))
    val screenState: State<SearchScreenState> = _screenState


    fun getUserByName(query: String) {
        if (query != "") {
            getUsersUseCase(query).onEach { result ->
                //Empty the the value of list each user search something
                _screenState.value = SearchScreenState(res = emptyList())
                when (result) {
                    is Resource.Success -> {
                        result.data?.items?.map { item ->
                            getUserUseCase(item.login).onEach { detailResult ->
                                if (detailResult is Resource.Success) {
                                    val oldData = mutableListOf<UserResponseDTO>()
                                    oldData.addAll(_screenState.value.res)
                                    detailResult.data?.let {
                                        oldData?.add(it)
                                    }
                                    _screenState.value = SearchScreenState(res = oldData)
                                }
                            }.launchIn(viewModelScope)
                        }

                    }
                    is Resource.Error -> {
                        _screenState.value = SearchScreenState(
                            isLoading = false,
                            mutableListOf(),
                            result.message ?: "An unexpected error occured"
                        )
                    }
                    is Resource.Loading -> {
                        _screenState.value = SearchScreenState(
                            isLoading = true,
                            mutableListOf(),
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
}