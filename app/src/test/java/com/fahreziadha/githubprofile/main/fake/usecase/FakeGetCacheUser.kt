package com.fahreziadha.githubprofile.main.fake.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fahreziadha.githubprofile.main.cacheUser1
import com.fahreziadha.githubprofile.main.cacheUser2
import com.fahreziadha.githubprofile.main.model.CacheUser
import com.fahreziadha.githubprofile.main.usecase.cache.GetCacheUsersUseCase

class FakeGetCacheUser : GetCacheUsersUseCase {
    override fun invoke(): LiveData<List<CacheUser>> {
        return MutableLiveData<List<CacheUser>>().apply {
            value = listOf(cacheUser1, cacheUser2)
        }
    }
}