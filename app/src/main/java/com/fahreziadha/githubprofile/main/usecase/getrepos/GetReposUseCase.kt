package com.fahreziadha.githubprofile.main.usecase.getrepos

import com.fahreziadha.githubprofile.main.model.GithubReposResponseDTO
import com.fahreziadha.githubprofile.main.model.UserResponseDTO
import com.fahreziadha.githubprofile.model.Result

interface GetReposUseCase {
    suspend operator fun invoke(login: String): Result<List<GithubReposResponseDTO>>
}