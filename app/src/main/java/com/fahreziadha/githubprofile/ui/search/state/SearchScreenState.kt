package com.fahreziadha.githubprofile.ui.search.state

import com.fahreziadha.githubprofile.data.remote.dto.Item
import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import com.fahreziadha.githubprofile.data.remote.dto.UserResponseDTO

data class SearchScreenState(
    val isLoading: Boolean = false,
    val res: List<UserResponseDTO> = emptyList(),
    val error: String = ""
)