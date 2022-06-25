package com.fahreziadha.githubprofile.main.framework.datasource

import com.fahreziadha.githubprofile.main.cacheUser1
import com.fahreziadha.githubprofile.main.framework.api.GithubApiClient
import com.fahreziadha.githubprofile.main.getUserResponseDTO1
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import  com.fahreziadha.githubprofile.model.Result


@ExperimentalCoroutinesApi
class UserRemoteDataSourceImplTest {


    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiClient: GithubApiClient

    private val client = OkHttpClient.Builder().build()

    private val moshi: Moshi = Moshi.Builder()
        .build()

    @Before
    fun createServer() {
        mockWebServer = MockWebServer()

        apiClient = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/")) // setting a dummy url
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
            .create(GithubApiClient::class.java)
    }

    @After
    fun shutdownServer() {
        mockWebServer.shutdown()
    }


    @Test
    fun `correct response is parsed into success result`() = runTest {
        // Arrange
        val response = MockResponse()
            .setBody(successfulResponse)
            .setResponseCode(200)

        mockWebServer.enqueue(response)

        val userRemoteDataSource = UserRemoteDataSourceImpl(apiClient)
        val expectedUser = getUserResponseDTO1

        // Act
        val result = userRemoteDataSource.getUsers("",5)

        // Assert
        assert(result is Result.Success)
        assertEquals((result as Result.Success).data, expectedUser)
    }


    @Test
    fun `malformed response returns json error result`() = runTest {
        // Arrange
        val response = MockResponse()
            .setBody(errorResponse)
            .setResponseCode(200)

        mockWebServer.enqueue(response)

        val userRemoteDataSource = UserRemoteDataSourceImpl(apiClient)

        // Act
        val result = userRemoteDataSource.getUsers("",5)

        // Assert
        assert(result is Result.Error)
        assert((result as Result.Error).error is JsonDataException)
    }

    @Test
    fun `error response returns http error result`() = runTest {
        // Arrange
        val response = MockResponse()
            .setBody(errorResponse)
            .setResponseCode(400)

        mockWebServer.enqueue(response)

        val activityRemoteDataSource = UserRemoteDataSourceImpl(apiClient)

        // Act
        val result = activityRemoteDataSource.getUsers("fahrezi",5)

        // Assert
        assert(result is Result.Error)
        assert((result as Result.Error).error is HttpException)
    }
}