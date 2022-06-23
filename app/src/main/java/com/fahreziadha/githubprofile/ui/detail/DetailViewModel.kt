package com.fahreziadha.githubprofile.ui.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahreziadha.githubprofile.common.Constants
import com.fahreziadha.githubprofile.common.Resource
import com.fahreziadha.githubprofile.domain.use_case.get_user.GetUserUseCase
import com.fahreziadha.githubprofile.ui.detail.state.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(DetailState())
    val state: State<DetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_ID)?.let { id ->
            getUser(id)
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
}