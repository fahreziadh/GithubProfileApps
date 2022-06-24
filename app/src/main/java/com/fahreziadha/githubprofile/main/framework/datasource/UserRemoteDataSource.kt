package com.fahreziadha.githubprofile.main.framework.datasource

import com.fahreziadha.githubprofile.main.model.GetUserResponseDTO
import com.fahreziadha.githubprofile.main.model.GithubReposResponseDTO
import com.fahreziadha.githubprofile.main.model.UserResponseDTO
import com.fahreziadha.githubprofile.model.Result

interface UserRemoteDataSource {
    suspend fun getUsers(query: String, page: Int): Result<GetUserResponseDTO>
    suspend fun getUser(login:String): Result<UserResponseDTO>
    suspend fun getRepos(login:String): Result<List<GithubReposResponseDTO>>
}