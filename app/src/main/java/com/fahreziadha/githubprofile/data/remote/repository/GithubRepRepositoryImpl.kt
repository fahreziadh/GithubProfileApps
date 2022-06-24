package com.fahreziadha.githubprofile.data.remote.repository

import com.fahreziadha.githubprofile.data.remote.GithubApi
import com.fahreziadha.githubprofile.data.remote.dto.GithubRepositoryResponseDTO
import com.fahreziadha.githubprofile.domain.repository.GithubRepRepository
import javax.inject.Inject

class GithubRepRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : GithubRepRepository {
    override suspend fun getRepository(username: String): List<GithubRepositoryResponseDTO> {
        return api.getUserRepository(username)
    }
}