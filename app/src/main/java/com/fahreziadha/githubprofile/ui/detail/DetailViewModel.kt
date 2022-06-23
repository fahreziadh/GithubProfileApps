package com.fahreziadha.githubprofile.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fahreziadha.githubprofile.common.Constants
import com.fahreziadha.githubprofile.domain.use_case.get_user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    init {
        savedStateHandle.get<String>(Constants.PARAM_ID)?.let { id ->
            println(id)
        }
    }
}