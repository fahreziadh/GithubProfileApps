package com.fahreziadha.githubprofile.main.usecase.getuser

import com.fahreziadha.githubprofile.main.model.User
import com.fahreziadha.githubprofile.main.model.UserResponseDTO
import com.fahreziadha.githubprofile.model.Result

interface GetUserUseCase {
    suspend operator fun invoke(login: String): Result<UserResponseDTO>
}