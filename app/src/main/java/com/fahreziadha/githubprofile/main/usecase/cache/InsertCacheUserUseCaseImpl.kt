package com.fahreziadha.githubprofile.main.usecase.cache

import com.fahreziadha.githubprofile.main.model.CacheUser
import com.fahreziadha.githubprofile.main.repository.UserRepository
import javax.inject.Inject

class InsertCacheUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : InsertCacheUserUseCase {
    override suspend fun invoke(cacheUser: CacheUser) {
        userRepository.saveUserCache(cacheUser)
    }
}