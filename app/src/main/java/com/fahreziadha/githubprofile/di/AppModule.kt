package com.fahreziadha.githubprofile.di

import com.fahreziadha.githubprofile.common.Constants
import com.fahreziadha.githubprofile.data.remote.GithubApi
import com.fahreziadha.githubprofile.data.repository.GithubProfileRepositoryImpl
import com.fahreziadha.githubprofile.data.repository.GithubRepRepositoryImpl
import com.fahreziadha.githubprofile.domain.repository.GithubProfileRepository
import com.fahreziadha.githubprofile.domain.repository.GithubRepRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGithubProfileaApi(): GithubApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(
                OkHttpClient().newBuilder()
                    .callTimeout(15, TimeUnit.SECONDS)
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGithubProfileRepository(api: GithubApi): GithubProfileRepository {
        return GithubProfileRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGithubRepRepository(api: GithubApi): GithubRepRepository {
        return GithubRepRepositoryImpl(api)
    }

}