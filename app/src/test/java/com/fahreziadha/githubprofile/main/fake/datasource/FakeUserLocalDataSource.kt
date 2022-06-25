package com.fahreziadha.githubprofile.main.fake.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fahreziadha.githubprofile.main.cacheUser1
import com.fahreziadha.githubprofile.main.framework.datasource.UserLocalDataSource
import com.fahreziadha.githubprofile.main.model.CacheUser

class FakeUserLocalDataSource : UserLocalDataSource {
    override fun getUsers(): LiveData<List<CacheUser>> {
        return MutableLiveData()
    }

    override suspend fun getUserById(id: Int): CacheUser {
        return cacheUser1
    }

    override suspend fun insertUser(user: CacheUser): Boolean {
        return false
    }

    override suspend fun deleteAllUser() {

    }
}