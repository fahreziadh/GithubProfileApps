package com.fahreziadha.githubprofile.main.usecase.getuser

import com.fahreziadha.githubprofile.main.model.UserResponseDTO
import com.fahreziadha.githubprofile.main.repository.UserRepository
import com.fahreziadha.githubprofile.model.Result
import javax.inject.Inject

class GetUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetUserUseCase {
    override suspend fun invoke(login: String): Result<UserResponseDTO> {
        return userRepository.getUser(login)
    }
}