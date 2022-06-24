package com.fahreziadha.githubprofile.main.repository


import androidx.lifecycle.LiveData
import com.fahreziadha.githubprofile.main.model.CacheUser
import com.fahreziadha.githubprofile.main.model.GithubReposResponseDTO
import com.fahreziadha.githubprofile.main.model.User
import com.fahreziadha.githubprofile.main.model.UserResponseDTO
import com.fahreziadha.githubprofile.model.Result

interface UserRepository {

    //remote
    suspend fun getSearchUsers(
        query: String,
        per_page: Int,
        page: Int
    ): List<User>

    suspend fun getUser(username: String): Result<UserResponseDTO>
    suspend fun getRepos(username: String): Result<List<GithubReposResponseDTO>>


    //local
    fun getCacheUsers(): LiveData<List<CacheUser>>
    suspend fun saveUserCache(cacheUser: CacheUser)
    suspend fun deleteAllCacheUsers()
}