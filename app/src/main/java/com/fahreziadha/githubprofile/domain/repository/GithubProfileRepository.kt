package com.fahreziadha.githubprofile.domain.repository

import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO

interface GithubProfileRepository {
    suspend fun getSearchUsers():SearchUserResponseDTO
}