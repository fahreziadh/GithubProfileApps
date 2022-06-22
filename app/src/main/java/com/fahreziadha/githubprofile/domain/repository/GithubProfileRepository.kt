package com.fahreziadha.githubprofile.domain.repository

interface GithubProfileRepository {
    suspend fun getSearchUsers():List<String>
}