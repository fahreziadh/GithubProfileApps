package com.fahreziadha.githubprofile.main.framework.datasource

import androidx.lifecycle.LiveData
import com.fahreziadha.githubprofile.main.model.CacheUser

interface UserLocalDataSource {

    fun getUsers(): LiveData<List<CacheUser>>

    suspend fun getUserById(id: Int): CacheUser?

    suspend fun insertUser(user: CacheUser)

    suspend fun deleteAllUser()
}