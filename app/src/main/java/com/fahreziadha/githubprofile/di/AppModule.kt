package com.fahreziadha.githubprofile.di

import android.app.Application
import androidx.room.Room
import com.fahreziadha.githubprofile.common.Constants
import com.fahreziadha.githubprofile.data.local.data_source.SearchDatabase
import com.fahreziadha.githubprofile.data.local.repository.SearchRepositoryImpl
import com.fahreziadha.githubprofile.data.remote.GithubApi
import com.fahreziadha.githubprofile.data.remote.repository.GithubProfileRepositoryImpl
import com.fahreziadha.githubprofile.data.remote.repository.GithubRepRepositoryImpl
import com.fahreziadha.githubprofile.domain.repository.GithubProfileRepository
import com.fahreziadha.githubprofile.domain.repository.GithubRepRepository
import com.fahreziadha.githubprofile.domain.repository.SearchRepository
import com.fahreziadha.githubprofile.domain.use_case.local.*
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

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): SearchDatabase {
        return Room.databaseBuilder(
            app,
            SearchDatabase::class.java,
            SearchDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideSearchRepository(db: SearchDatabase): SearchRepository {
        return SearchRepositoryImpl(db.searchDao)
    }

    @Provides
    @Singleton
    fun provideSearchUseCases(repository: SearchRepository): LocalUseCase {
        return LocalUseCase(
            getListSearch = GetListSearch(repository = repository),
            getSearch = GetSearch(repository = repository),
            deleteSearch = DeleteSearch(repository = repository),
            addSearch = AddSearch(repository = repository)
        )
    }

}