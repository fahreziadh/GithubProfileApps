package com.fahreziadha.githubprofile.ui.detail.state

import com.fahreziadha.githubprofile.data.remote.dto.UserResponseDTO

data class DetailState(
    val isLoading: Boolean = false,
    val user: UserResponseDTO? = null,
    val error: String = ""
)
