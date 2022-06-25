package com.fahreziadha.githubprofile.main.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.fahreziadha.githubprofile.main.cacheUser1
import com.fahreziadha.githubprofile.main.cacheUser2
import com.fahreziadha.githubprofile.main.fake.usecase.FakeDeleteCache
import com.fahreziadha.githubprofile.main.fake.usecase.FakeGetCacheUser
import com.fahreziadha.githubprofile.main.fake.usecase.FakeGetUsers
import com.fahreziadha.githubprofile.main.fake.usecase.FakeInsertCache
import com.fahreziadha.githubprofile.main.model.CacheUser
import com.fahreziadha.githubprofile.main.usecase.cache.DeleteCacheUserUseCase
import com.fahreziadha.githubprofile.main.usecase.cache.GetCacheUsersUseCase
import com.fahreziadha.githubprofile.main.usecase.cache.InsertCacheUserUseCase
import com.fahreziadha.githubprofile.main.usecase.getuser.GetUserUseCase
import com.fahreziadha.githubprofile.main.usecase.getusers.GetUsersUseCase
import com.fahreziadha.githubprofile.util.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*
import org.junit.Assert.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutineRule()

    private val mockGetCacheUser: GetCacheUsersUseCase = mock()
    private val mockInsertCacheUsersUseCase: InsertCacheUserUseCase = mock()
    private val mockDeleteCacheUsersUseCase: DeleteCacheUserUseCase = mock()
    private val mockGetUsers: GetUsersUseCase = mock()
    private val cacheListObserver: Observer<SearchScreenState> = mock()

    @Captor
    private lateinit var cacheListCaptor: ArgumentCaptor<SearchScreenState>

    @Test
    fun `the view model maps list of activities to list ui state`() {

        //Arrange
        val liveDataToReturn = MutableLiveData<List<CacheUser>>().apply {
            value = listOf(
                cacheUser1,
                cacheUser2
            )
        }

        val expectedList = listOf(cacheUser1, cacheUser2)

        whenever(mockGetCacheUser.invoke()).doReturn(liveDataToReturn)

        val viewModel = SearchViewModel(
            mockGetCacheUser,
            mockGetUsers,
            mockInsertCacheUsersUseCase,
            mockDeleteCacheUsersUseCase
        )

        //Act
        viewModel.uiStateCacheUser.observeForever(cacheListObserver)

        //Assert
        verify(cacheListObserver, times(1)).onChanged(cacheListCaptor.capture())
        assert(cacheListCaptor.value is SearchScreenState.Idle)

        val actualList = (cacheListCaptor.value as SearchScreenState.Idle).res
        assertEquals(actualList, expectedList)
    }

    @Test
    fun `the view model maps empty list of activities to empty ui state`() {
        // Arrange
        val liveDataToReturn = MutableLiveData<List<CacheUser>>()
            .apply { value = listOf() }

        whenever(mockGetCacheUser.invoke()).doReturn(liveDataToReturn)

        val viewModel = SearchViewModel(
            mockGetCacheUser,
            mockGetUsers,
            mockInsertCacheUsersUseCase,
            mockDeleteCacheUsersUseCase
        )

        // Act
        viewModel.uiStateCacheUser.observeForever(cacheListObserver)

        // Assert
        verify(cacheListObserver, times(1)).onChanged(cacheListCaptor.capture())
        assert(cacheListCaptor.value is SearchScreenState.Idle)
    }


    @Test
    fun `calling clearUserCache() interacts with the correct use case`() = runTest {
        // Arrange
        val viewModel = SearchViewModel(
            mockGetCacheUser,
            mockGetUsers,
            mockInsertCacheUsersUseCase,
            mockDeleteCacheUsersUseCase
        )


        // Act
        viewModel.clearUserCache()
        advanceUntilIdle() // works the same as runCurrent() in this case
//
//        // Assert
//        verify(mockDeleteCacheUsersUseCase, times(1))
    }
}