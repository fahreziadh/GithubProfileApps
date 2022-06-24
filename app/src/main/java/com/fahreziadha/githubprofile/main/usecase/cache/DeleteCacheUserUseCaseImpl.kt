package com.fahreziadha.githubprofile.main.usecase.cache

import com.fahreziadha.githubprofile.main.repository.UserRepository
import javax.inject.Inject

class DeleteCacheUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : DeleteCacheUserUseCase {
    override suspend fun invoke() {
        userRepository.deleteAllCacheUsers()
    }
}