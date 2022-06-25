package com.fahreziadha.githubprofile.main.fake.usecase

import com.fahreziadha.githubprofile.main.model.CacheUser
import com.fahreziadha.githubprofile.main.usecase.cache.InsertCacheUserUseCase

class FakeInsertCache(
    private val isCacheSaved: Boolean = false
) : InsertCacheUserUseCase {
    override suspend fun invoke(cacheUser: CacheUser): Boolean {
        return isCacheSaved
    }
}