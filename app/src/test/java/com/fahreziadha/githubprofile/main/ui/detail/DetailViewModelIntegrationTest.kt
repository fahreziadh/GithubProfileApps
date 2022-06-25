package com.fahreziadha.githubprofile.main.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.fahreziadha.githubprofile.main.cacheUser1
import com.fahreziadha.githubprofile.main.framework.api.GithubApiClient
import com.fahreziadha.githubprofile.main.framework.datasource.UserLocalDataSourceImpl
import com.fahreziadha.githubprofile.main.framework.datasource.UserRemoteDataSourceImpl
import com.fahreziadha.githubprofile.main.framework.db.SearchDao
import com.fahreziadha.githubprofile.main.githubReposResponseDTO1
import com.fahreziadha.githubprofile.main.repository.UserRepositoryImpl
import com.fahreziadha.githubprofile.main.ui.search.SearchViewModel
import com.fahreziadha.githubprofile.main.usecase.cache.DeleteCacheUserUseCaseImpl
import com.fahreziadha.githubprofile.main.usecase.cache.GetCacheUsersUseCaseImpl
import com.fahreziadha.githubprofile.main.usecase.cache.InsertCacheUserUseCaseImpl
import com.fahreziadha.githubprofile.main.usecase.getrepos.GetReposUseCaseImpl
import com.fahreziadha.githubprofile.main.usecase.getuser.GetUserUseCaseImpl
import com.fahreziadha.githubprofile.main.usecase.getusers.GetUsersUseCaseImpl
import com.fahreziadha.githubprofile.util.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class DetailViewModelIntegrationTest {
    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)
    private val testScope = TestScope(testDispatcher)

    @get:Rule
    val coroutineRule = CoroutineRule(testDispatcher)

    private val mockApiClient: GithubApiClient = mock()
    private val mockSearchDao: SearchDao = mock()

    private val remoteDataSource = UserRemoteDataSourceImpl(mockApiClient)
    private val localDataSource = UserLocalDataSourceImpl(mockSearchDao)

    private val userRepository = UserRepositoryImpl(
        appScope = testScope,
        ioDispatcher = testDispatcher,
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource
    )
    private val getUsersUseCase = GetUsersUseCaseImpl(userRepository)
    private val getUserUseCase = GetUserUseCaseImpl(userRepository)
    private val getUserCache = GetCacheUsersUseCaseImpl(userRepository)
    private val saveUserCache = InsertCacheUserUseCaseImpl(userRepository)
    private val getReposUseCase = GetReposUseCaseImpl(userRepository)
    private val deleteUserCache = DeleteCacheUserUseCaseImpl(userRepository)
    private val insertCache = InsertCacheUserUseCaseImpl(userRepository)
    private val savedStateHandle = mock<SavedStateHandle>()

    @Test
    fun `calling load User() triggers the api client`() = runTest {
        // Arrange
        val viewModel = DetailViewModel(
            getUserUseCase,
            getReposUseCase,
            savedStateHandle,
        )

        // Act
        viewModel.getUser("fahrezi")
        runCurrent()

        // Assert
        verify(mockApiClient, times(1)).getUser("fahrezi")
    }

    @Test
    fun `calling save user triggers the dao insert`() = runTest {
        // Arrange
        val viewModel = DetailViewModel(
            getUserUseCase,
            getReposUseCase,
            savedStateHandle,
        )

        // Act
        viewModel.getUserRepository("fahrezi")
        runCurrent()

        // Assert
        verify(mockApiClient, times(1)).getUserRepos("fahrezi")
    }

}