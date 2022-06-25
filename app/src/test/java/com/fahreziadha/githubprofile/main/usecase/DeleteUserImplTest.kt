package com.fahreziadha.githubprofile.main.usecase

import com.fahreziadha.githubprofile.main.cacheUser1
import com.fahreziadha.githubprofile.main.repository.UserRepository
import com.fahreziadha.githubprofile.main.usecase.cache.DeleteCacheUserUseCaseImpl
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
class DeleteUserImplTest {
    private val mockUserRepository:UserRepository= mock()

    @Test
    fun `delete users interacts with repository`() = runTest {
        // Arrange
        val deleteActivity = DeleteCacheUserUseCaseImpl(mockUserRepository)

        // Act
        deleteActivity()

        // Assert
        verify(mockUserRepository, times(1)).deleteAllCacheUsers()
    }
}