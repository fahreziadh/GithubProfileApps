package com.fahreziadha.githubprofile.data.repository

import com.fahreziadha.githubprofile.data.remote.GithubProfileApi
import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import com.fahreziadha.githubprofile.data.remote.dto.UserResponseDTO
import com.fahreziadha.githubprofile.domain.repository.GithubProfileRepository
import javax.inject.Inject

class GithubProfileRepositoryImpl @Inject constructor(
    private val api: GithubProfileApi
) : GithubProfileRepository {
    override suspend fun getSearchUsers(query: String): SearchUserResponseDTO {
        return api.getSearchUser(query)
    }

    override suspend fun getUser(username: String): UserResponseDTO {
        return api.getUser(username)
    }
}