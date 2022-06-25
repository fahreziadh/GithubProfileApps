package com.fahreziadha.githubprofile.di

import com.fahreziadha.githubprofile.di.annotation.BaseUrl
import com.fahreziadha.githubprofile.main.framework.api.GithubApiClient
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
object TestNetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideMockServer(): MockWebServer {
        return MockWebServer()
    }

    /**
     * We need to jump through the hoops a bit
     * to avoid NetworkOnMainThread exception
     * in our UI tests.
     */
    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(mockWebServer: MockWebServer): String {
        var url = ""
        val thread = Thread {
            url = mockWebServer.url("/").toString()
        }
        thread.start()
        thread.join()
        return url
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
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit): GithubApiClient {
        return retrofit.create(GithubApiClient::class.java)
    }
}