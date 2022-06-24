package com.fahreziadha.githubprofile.main.usecase.getrepos

import com.fahreziadha.githubprofile.main.model.GithubReposResponseDTO
import com.fahreziadha.githubprofile.main.repository.UserRepository
import com.fahreziadha.githubprofile.model.Result
import javax.inject.Inject

class GetReposUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetReposUseCase {
    override suspend fun invoke(login: String): Result<List<GithubReposResponseDTO>> {
        return userRepository.getRepos(login)
    }
}