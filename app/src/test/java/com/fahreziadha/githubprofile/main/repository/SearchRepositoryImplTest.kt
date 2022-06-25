package com.fahreziadha.githubprofile.main.repository

import com.fahreziadha.githubprofile.main.fake.datasource.FakeUserLocalDataSource
import com.fahreziadha.githubprofile.main.fake.datasource.FakeUserRemoteDataSource
import com.fahreziadha.githubprofile.main.userResponseDTO1
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import com.fahreziadha.githubprofile.model.Result
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Assert.assertEquals

@ExperimentalCoroutinesApi
class SearchRepositoryImplTest {
    @Test
    fun `getNewUsers() returns a result after switching the context`() = runTest {
        // Arrange
        val userRepository = UserRepositoryImpl(
            appScope = this,
            ioDispatcher = StandardTestDispatcher(testScheduler),
            remoteDataSource = FakeUserRemoteDataSource(),
            localDataSource = FakeUserLocalDataSource()
        )

        val expectedUsers = userResponseDTO1

        // Act
        val result = userRepository.getUser("")

        // Assert
        assert(result is Result.Success)
        assertEquals((result as Result.Success).data, expectedUsers)
    }


    @Test
    fun `getNewUsersInANewCoroutine correctly calls remote data source`() = runTest {
        // Arrange
        val fakeRemoteRepository = FakeUserRemoteDataSource()
        val activityRepository = UserRepositoryImpl(
            appScope = this,
            ioDispatcher = StandardTestDispatcher(testScheduler),
            remoteDataSource = fakeRemoteRepository,
            localDataSource = FakeUserLocalDataSource()
        )

        // Act
        activityRepository.getUserInANewCoroutine()
        advanceUntilIdle()

        // Assert
        assert(fakeRemoteRepository.getUserWasCalled)
    }
}