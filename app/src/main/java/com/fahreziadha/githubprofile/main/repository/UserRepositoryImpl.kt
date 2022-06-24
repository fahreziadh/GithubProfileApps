package com.fahreziadha.githubprofile.main.repository

import androidx.lifecycle.LiveData
import com.fahreziadha.githubprofile.di.annotation.AppScope
import com.fahreziadha.githubprofile.di.annotation.IODispatcher
import com.fahreziadha.githubprofile.main.framework.datasource.UserLocalDataSource
import com.fahreziadha.githubprofile.main.framework.datasource.UserRemoteDataSource
import com.fahreziadha.githubprofile.main.model.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import com.fahreziadha.githubprofile.model.Result
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    @AppScope private val appScope: CoroutineScope,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {

    override suspend fun getSearchUsers(
        query: String,
        per_page: Int,
        page: Int
    ): List<User> {
        return withContext(ioDispatcher) {
            var listUser = mutableListOf<User>()
            var users = remoteDataSource.getUsers(query = query, page = page)
            when (users) {
                is Result.Success -> {
                    users.data.items.map { user ->
                        val res = remoteDataSource.getUser(user.login)
                        if (res is Result.Success){
                            println(res.data)
                            listUser.add(User(
                                id = res.data.id,
                                name = res.data.name ?: "",
                                login = res.data.login ?: "",
                                location = res.data.location ?: "",
                                bio = res.data.bio ?: "",
                                email = res.data.email ?: "",
                                avatarUrl = res.data.avatar_url ?: "",
                                company = res.data.company ?: ""
                            ))
                        }
                    }
                }
                is Result.Error -> {
                }
            }
            listUser
        }
    }

    override suspend fun getRepos(username: String): Result<List<GithubReposResponseDTO>> {
        return withContext(ioDispatcher) {
            remoteDataSource.getRepos(username)
        }
    }

    override suspend fun getUser(username: String): Result<UserResponseDTO> {
        return withContext(ioDispatcher) {
            remoteDataSource.getUser(username)
        }
    }

    override fun getCacheUsers(): LiveData<List<CacheUser>> {
        return localDataSource.getUsers()
    }

    override suspend fun deleteAllCacheUsers() {
        localDataSource.deleteAllUser()
    }

    override suspend fun saveUserCache(cacheUser: CacheUser) {
        localDataSource.insertUser(cacheUser)
    }
}