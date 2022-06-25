package com.fahreziadha.githubprofile.main.ui.search

import com.fahreziadha.githubprofile.main.model.CacheUser
import com.fahreziadha.githubprofile.main.model.User


sealed class SearchScreenState {
    data class Success(val res: List<User>,val isLoadMoreLoadingActive:Boolean=false) : SearchScreenState()
    data class Error(val exception: Throwable) : SearchScreenState()
    object Loading : SearchScreenState()
    data class Idle(val res: List<CacheUser>) : SearchScreenState()
}