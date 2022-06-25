package com.fahreziadha.githubprofile.main.fake.usecase

import com.fahreziadha.githubprofile.main.usecase.cache.DeleteCacheUserUseCase

class FakeDeleteCache : DeleteCacheUserUseCase {
    var wasCalled = false
        private set

    override suspend fun invoke() {
        wasCalled = true
    }
}