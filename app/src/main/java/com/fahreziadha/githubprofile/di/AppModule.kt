package com.fahreziadha.githubprofile.di

import com.fahreziadha.githubprofile.common.Constants
import com.fahreziadha.githubprofile.data.remote.GithubProfileApi
import com.fahreziadha.githubprofile.data.repository.GithubProfileRepositoryImpl
import com.fahreziadha.githubprofile.domain.repository.GithubProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): GithubProfileApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(
                OkHttpClient().newBuilder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubProfileApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: GithubProfileApi): GithubProfileRepository {
        return GithubProfileRepositoryImpl(api)
    }
}