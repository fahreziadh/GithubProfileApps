package com.fahreziadha.githubprofile.domain.repository

import com.fahreziadha.githubprofile.data.remote.dto.GithubRepositoryResponseDTO

interface GithubRepRepository {
    suspend fun getRepository(username:String): List<GithubRepositoryResponseDTO>
}