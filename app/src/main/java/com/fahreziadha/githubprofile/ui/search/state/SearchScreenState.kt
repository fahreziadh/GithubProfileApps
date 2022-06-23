package com.fahreziadha.githubprofile.ui.search.state

import com.fahreziadha.githubprofile.domain.model.User


data class SearchScreenState(
    val isLoading: Boolean = false,
    val res: List<User> = emptyList(),
    val error: String = ""
)