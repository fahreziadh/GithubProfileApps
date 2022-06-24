package com.fahreziadha.githubprofile.main.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahreziadha.githubprofile.main.usecase.getrepos.GetReposUseCase
import com.fahreziadha.githubprofile.main.usecase.getuser.GetUserUseCase
import com.fahreziadha.githubprofile.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getReposUseCase: GetReposUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiUserState =
        MutableStateFlow<DetailScreenUserState>(DetailScreenUserState.Loading)
    val uiUserState: StateFlow<DetailScreenUserState> = _uiUserState

    private val _uiReposState =
        MutableStateFlow<DetailScreenReposState>(DetailScreenReposState.Loading)
    val uiReposState: StateFlow<DetailScreenReposState> = _uiReposState

    init {
        savedStateHandle.get<String>("id")?.let { id ->
            getUser(id)
            getUserRepository(id)
        }
    }

    private var loadingJob: Job? = null

    private fun getUser(userName: String) {
        _uiUserState.value = DetailScreenUserState.Loading
        loadingJob?.cancel()
        loadingJob = viewModelScope.launch {
            val newUiState = when (val result = getUserUseCase(userName)) {
                is Result.Success -> {
                    DetailScreenUserState.Success(res = result.data)
                }
                is Result.Error -> {
                    result.error
                        .takeUnless { it is CancellationException }
                        ?.let(DetailScreenUserState::Error)
                        ?: DetailScreenUserState.Loading
                }
            }
            _uiUserState.value = (newUiState)
        }
    }

    private fun getUserRepository(userName: String) {
        _uiReposState.value = DetailScreenReposState.Loading

        loadingJob = viewModelScope.launch {
            val newUiState = when (val result = getReposUseCase(userName)) {
                is Result.Success -> {
                    DetailScreenReposState.Success(res = result.data)
                }
                is Result.Error -> {
                    result.error
                        .takeUnless { it is CancellationException }
                        ?.let(DetailScreenReposState::Error)
                        ?: DetailScreenReposState.Loading
                }
            }
            _uiReposState.value = (newUiState)
        }
    }
}