package com.fahreziadha.githubprofile.di

import android.content.Context
import com.fahreziadha.githubprofile.BuildConfig
import com.fahreziadha.githubprofile.di.annotation.BaseUrl
import com.fahreziadha.githubprofile.main.framework.api.GithubApiClient
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val HTTP_CACHE_SIZE = 1024 * 1024 * 1024L
    private const val CONNECTION_TIMEOUT_SECONDS = 600L

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = BuildConfig.API_URL

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        val cacheDirName = "okhttp_cache"

        val cacheDirectory = context.getDir(cacheDirName, Context.MODE_PRIVATE)
        return Cache(cacheDirectory, HTTP_CACHE_SIZE)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .readTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        @BaseUrl baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit): GithubApiClient {
        return retrofit.create(GithubApiClient::class.java)
    }
}