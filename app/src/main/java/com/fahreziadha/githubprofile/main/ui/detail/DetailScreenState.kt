package com.fahreziadha.githubprofile.main.ui.detail

import com.fahreziadha.githubprofile.main.model.GithubReposResponseDTO
import com.fahreziadha.githubprofile.main.model.GithubRepository
import com.fahreziadha.githubprofile.main.model.UserResponseDTO


sealed class DetailScreenUserState {
    data class Success(val res: UserResponseDTO) : DetailScreenUserState()
    data class Error(val exception: Throwable) : DetailScreenUserState()
    object Loading : DetailScreenUserState()
}

sealed class DetailScreenReposState {
    data class Success(val res: List<GithubReposResponseDTO>) : DetailScreenReposState()
    data class Error(val exception: Throwable) : DetailScreenReposState()
    object Loading : DetailScreenReposState()

}
