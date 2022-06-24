package com.fahreziadha.githubprofile.main.usecase.cache

import androidx.lifecycle.LiveData
import com.fahreziadha.githubprofile.main.model.CacheUser
import com.fahreziadha.githubprofile.main.repository.UserRepository
import javax.inject.Inject

class GetCacheUsersUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetCacheUsersUseCase {
    override fun invoke(): LiveData<List<CacheUser>> {
        return userRepository.getCacheUsers()
    }
}