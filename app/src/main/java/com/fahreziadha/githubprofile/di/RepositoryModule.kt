package com.fahreziadha.githubprofile.di

import com.fahreziadha.githubprofile.main.repository.UserRepositoryImpl
import com.fahreziadha.githubprofile.main.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}