package com.fahreziadha.githubprofile.ui.detail.state

import com.fahreziadha.githubprofile.data.remote.dto.UserResponseDTO
import com.fahreziadha.githubprofile.domain.model.GithubRepository

data class RepositoryState(
    val isLoading: Boolean = false,
    val res: List<GithubRepository> = emptyList(),
    val error: String = ""
)