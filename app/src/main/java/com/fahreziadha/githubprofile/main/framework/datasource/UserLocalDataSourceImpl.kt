package com.fahreziadha.githubprofile.main.framework.datasource

import androidx.lifecycle.LiveData
import com.fahreziadha.githubprofile.main.framework.db.SearchDao
import com.fahreziadha.githubprofile.main.model.CacheUser
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(private val searchDao: SearchDao) :
    UserLocalDataSource {
    override fun getUsers(): LiveData<List<CacheUser>> {
        return searchDao.getUsers()
    }

    override suspend fun getUserById(id: Int): CacheUser? {
        return searchDao.getUserById(id)
    }

    override suspend fun insertUser(user: CacheUser): Boolean {
        searchDao.insertUser(user)
        return true
    }

    override suspend fun deleteAllUser() {
        searchDao.deleteAllUser()
    }
}