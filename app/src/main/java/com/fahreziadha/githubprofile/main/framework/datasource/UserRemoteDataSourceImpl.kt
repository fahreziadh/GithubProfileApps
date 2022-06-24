package com.fahreziadha.githubprofile.main.framework.datasource

import com.fahreziadha.githubprofile.main.framework.api.GithubApiClient
import com.fahreziadha.githubprofile.main.model.GetUserResponseDTO
import com.fahreziadha.githubprofile.main.model.GithubReposResponseDTO
import com.fahreziadha.githubprofile.main.model.UserResponseDTO
import com.fahreziadha.githubprofile.model.Result
import com.fahreziadha.githubprofile.model.runCatching
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val githubApiClient: GithubApiClient
) : UserRemoteDataSource {

    override suspend fun getUsers(query: String, page: Int): Result<GetUserResponseDTO> {
        return runCatching {
            githubApiClient.getSearchUser(query = query, page = page)
        }
    }

    override suspend fun getUser(login: String): Result<UserResponseDTO> {
        return runCatching {
            githubApiClient.getUser(login = login)
        }
    }

    override suspend fun getRepos(login: String): Result<List<GithubReposResponseDTO>> {
        return runCatching {
            githubApiClient.getUserRepos(login)
        }
    }
}