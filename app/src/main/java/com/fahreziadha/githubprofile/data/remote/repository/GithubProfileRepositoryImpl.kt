package com.fahreziadha.githubprofile.data.remote.repository

import com.fahreziadha.githubprofile.data.remote.GithubApi
import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import com.fahreziadha.githubprofile.data.remote.dto.UserResponseDTO
import com.fahreziadha.githubprofile.domain.repository.GithubProfileRepository
import javax.inject.Inject

class GithubProfileRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : GithubProfileRepository {
    override suspend fun getSearchUsers(query: String, per_page:Int, page: Int): SearchUserResponseDTO {
        return api.getSearchUser(query = query, per_page = per_page, page = page)
    }

    override suspend fun getUser(username: String): UserResponseDTO {
        return api.getUser(username)
    }
}