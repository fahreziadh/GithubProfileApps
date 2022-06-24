package com.fahreziadha.githubprofile.di

import com.fahreziadha.githubprofile.main.framework.datasource.UserLocalDataSource
import com.fahreziadha.githubprofile.main.framework.datasource.UserLocalDataSourceImpl
import com.fahreziadha.githubprofile.main.framework.datasource.UserRemoteDataSource
import com.fahreziadha.githubprofile.main.framework.datasource.UserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindUserRemoteDataSource(impl: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    abstract fun bindUserLocalDataSource(impl: UserLocalDataSourceImpl): UserLocalDataSource

}