package com.fahreziadha.githubprofile.ui.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahreziadha.githubprofile.common.Resource
import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import com.fahreziadha.githubprofile.domain.use_case.get_users.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _state = mutableStateOf(String())
    val state: State<String> = _state

    init {
        getUserByName()
    }

    fun getUserByName() {
        getUsersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = result.data.toString()
                }
                is Resource.Error -> {
                    _state.value = result.message ?: "An unexpected error occured"
                }
                is Resource.Loading -> {
                    _state.value = "Loading..."
                }
            }
        }
    }
}