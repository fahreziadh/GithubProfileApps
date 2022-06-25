package com.fahreziadha.githubprofile.main.usecase

import com.fahreziadha.githubprofile.main.repository.UserRepository
import com.fahreziadha.githubprofile.main.usecase.cache.DeleteCacheUserUseCaseImpl
import com.fahreziadha.githubprofile.main.usecase.cache.GetCacheUsersUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCacheUserImplTest {
    private val mockUserRepository: UserRepository = mock()
    // Arrange


    @Test
    fun `get users interacts with repository`() = runTest {
        // Arrange
        val getUserCache = GetCacheUsersUseCaseImpl(mockUserRepository)

        // Act
        getUserCache()

        // Assert
        verify(mockUserRepository, times(1)).getCacheUsers()
    }
}