package com.fahreziadha.githubprofile.main.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.fahreziadha.githubprofile.main.usecase.getrepos.GetReposUseCase
import com.fahreziadha.githubprofile.main.usecase.getuser.GetUserUseCase
import com.fahreziadha.githubprofile.util.CoroutineRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutineRule()


    private val getUserUseCase: GetUserUseCase = mock()
    private val getRepoUseCase: GetReposUseCase = mock()
    private val savedStateHandle = mock<SavedStateHandle>()

    @Test
    fun `the view model show user to ui state`() {

        val viewModel = DetailViewModel(
            getUserUseCase,
            getRepoUseCase,
            savedStateHandle
        )

        // Act
        viewModel.uiReposState

        // Assert
        assert(viewModel.uiUserState.value is DetailScreenUserState.Loading)

    }

    @Test
    fun `creating a viewmodel updates ui state to success after loading`() {
        // Arrange

        val viewModel = DetailViewModel(
            getUserUseCase,
            getRepoUseCase,
            savedStateHandle
        )

        val expectedUiState = DetailScreenReposState.Loading

        // Act
        coroutineRule.testDispatcher.scheduler.runCurrent()

        // Assert
        val actualState = viewModel.uiReposState.value
        assertEquals(actualState, expectedUiState)
    }
}