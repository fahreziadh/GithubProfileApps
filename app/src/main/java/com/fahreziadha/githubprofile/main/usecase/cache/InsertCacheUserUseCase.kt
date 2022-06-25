package com.fahreziadha.githubprofile.main.usecase.cache

import com.fahreziadha.githubprofile.main.model.CacheUser

interface InsertCacheUserUseCase {
    suspend operator fun invoke(cacheUser: CacheUser): Boolean
}