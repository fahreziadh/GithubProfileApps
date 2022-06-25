package com.fahreziadha.githubprofile.main.fake.datasource

import com.fahreziadha.githubprofile.main.framework.datasource.UserRemoteDataSource
import com.fahreziadha.githubprofile.main.getUserResponseDTO1
import com.fahreziadha.githubprofile.main.githubReposResponseDTO1
import com.fahreziadha.githubprofile.main.model.GetUserResponseDTO
import com.fahreziadha.githubprofile.main.model.GithubReposResponseDTO
import com.fahreziadha.githubprofile.main.model.UserResponseDTO
import com.fahreziadha.githubprofile.main.userResponseDTO1
import com.fahreziadha.githubprofile.model.Result

class FakeUserRemoteDataSource : UserRemoteDataSource {
    var getUserWasCalled = false
        private set

    override suspend fun getUsers(query: String, page: Int): Result<GetUserResponseDTO> {
        getUserWasCalled = true
        return Result.Success(getUserResponseDTO1)
    }

    override suspend fun getUser(login: String): Result<UserResponseDTO> {
        getUserWasCalled = true
        return Result.Success(userResponseDTO1)
    }

    override suspend fun getRepos(login: String): Result<List<GithubReposResponseDTO>> {
        getUserWasCalled = true
        return Result.Success(listOf(githubReposResponseDTO1))
    }
}