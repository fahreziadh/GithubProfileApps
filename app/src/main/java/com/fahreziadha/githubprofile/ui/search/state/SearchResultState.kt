package com.fahreziadha.githubprofile.ui.search.state

import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import com.fahreziadha.githubprofile.data.remote.dto.UserResponseDTO

data class SearchResultState(
    val isLoading: Boolean = false,
    val res: UserResponseDTO? = null,
    val error: String = ""
)