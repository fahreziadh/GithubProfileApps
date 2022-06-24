package com.fahreziadha.githubprofile.di

import com.fahreziadha.githubprofile.main.usecase.cache.*
import com.fahreziadha.githubprofile.main.usecase.getrepos.GetReposUseCase
import com.fahreziadha.githubprofile.main.usecase.getrepos.GetReposUseCaseImpl
import com.fahreziadha.githubprofile.main.usecase.getuser.GetUserUseCase
import com.fahreziadha.githubprofile.main.usecase.getuser.GetUserUseCaseImpl
import com.fahreziadha.githubprofile.main.usecase.getusers.GetUsersUseCase
import com.fahreziadha.githubprofile.main.usecase.getusers.GetUsersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    //remote
    @Binds
    abstract fun bindGetUsers(implGetUsersUseCaseImpl: GetUsersUseCaseImpl): GetUsersUseCase

    @Binds
    abstract fun bindGetRepos(implgetReposUseCaseImpl: GetReposUseCaseImpl): GetReposUseCase

    @Binds
    abstract fun bindGetUser(implGetUserUseCaseImpl: GetUserUseCaseImpl): GetUserUseCase

    //local
    @Binds
    abstract fun bindDeleteCacheUser(implDeleteCacheUserUseCase: DeleteCacheUserUseCaseImpl): DeleteCacheUserUseCase

    @Binds
    abstract fun bindGetCacheUser(implGetUsersUseCaseImpl: GetCacheUsersUseCaseImpl): GetCacheUsersUseCase

    @Binds
    abstract fun bindInsertCacheUser(implInsertCacheUserUseCase: InsertCacheUserUseCaseImpl): InsertCacheUserUseCase

}