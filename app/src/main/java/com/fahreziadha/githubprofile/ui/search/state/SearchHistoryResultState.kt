package com.fahreziadha.githubprofile.ui.search.state

import com.fahreziadha.githubprofile.domain.model.CacheUser
import com.fahreziadha.githubprofile.domain.model.User

data class SearchHistoryResultState(
    val isLoading: Boolean = false,
    val res: List<CacheUser> = emptyList(),
    val error: String = ""
)