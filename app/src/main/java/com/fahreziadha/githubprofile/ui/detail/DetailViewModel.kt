package com.fahreziadha.githubprofile.ui.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahreziadha.githubprofile.common.Constants
import com.fahreziadha.githubprofile.common.Resource
import com.fahreziadha.githubprofile.domain.use_case.get_gitrepository.GetGitRepositoryUseCase
import com.fahreziadha.githubprofile.domain.use_case.get_user.GetUserUseCase
import com.fahreziadha.githubprofile.ui.detail.state.DetailState
import com.fahreziadha.githubprofile.ui.detail.state.RepositoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getGitRepositoryUseCase: GetGitRepositoryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(DetailState())
    val state: State<DetailState> = _state

    private val _stateRep = mutableStateOf(RepositoryState())
    val stateRep: State<RepositoryState> = _stateRep

    init {
        savedStateHandle.get<String>(Constants.PARAM_ID)?.let { id ->
            getUser(id)
            getUserRepository(id)
        }
    }


    private fun getUser(userName: String) {
        getUserUseCase(userName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = DetailState(user = result.data)
                }
                is Resource.Error -> {
                    _state.value = DetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = DetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getUserRepository(userName: String) {
        getGitRepositoryUseCase(userName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let { data ->
                        _stateRep.value = RepositoryState(res = result.data)
                    }
                }
                is Resource.Error -> {
                    _stateRep.value = RepositoryState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _stateRep.value = RepositoryState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}