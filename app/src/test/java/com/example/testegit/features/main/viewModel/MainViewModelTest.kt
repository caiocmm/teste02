package com.example.testegit.features.main.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.testegit.core.data.BaseResponse
import com.example.testegit.core.network.ResponseWrapper
import com.example.testegit.features.core.test.InstantTaskExecutor
import com.example.testegit.features.main.data.repository.MainRepository
import com.example.testegit.features.main.data.response.GitResponse
import com.example.testegit.features.main.viewState.MainViewState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verifySequence
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.core.context.stopKoin

@ExtendWith(InstantTaskExecutor::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel

    @RelaxedMockK
    private lateinit var mainRepository: MainRepository

    @RelaxedMockK
    private lateinit var stateObserver: Observer<MainViewState>

    @BeforeEach
    fun start() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(mainRepository)
        mainViewModel.state.observeForever(stateObserver)
    }

    @AfterEach
    fun clean() {
        unmockkAll()
        stopKoin()
    }

    @Test
    fun `When call getGitList Should get all Git Repositories with success`() {

        val mockSuccess = mockk<BaseResponse<GitResponse>>()

        every { mockSuccess.items } returns mockk(relaxed = true)

        coEvery { mainRepository.getGitList(any(), any(), any()) } returns ResponseWrapper.Success(
            mockSuccess
        )

        val data: Observer<List<GitResponse>> = mockk(relaxed = true)
        mainViewModel.gitList.observeForever(data)

        mainViewModel.getGitList()

        verifySequence {
            stateObserver.onChanged(MainViewState.ShowLoading(true))
            data.onChanged(mainViewModel.gitList.value)
            stateObserver.onChanged(MainViewState.ShowLoading(false))
        }
    }

    @Test
    fun `When call getGitList Should get all Git Repositories with Error`() {

        val error = "api error"

        coEvery { mainRepository.getGitList(any(), any(), any()) } returns ResponseWrapper.Error(
            400, error
        )

        mainViewModel.getGitList()

        verifySequence {
            stateObserver.onChanged(MainViewState.ShowLoading(true))
            stateObserver.onChanged(MainViewState.Error(error))
            stateObserver.onChanged(MainViewState.ShowLoading(false))
        }
    }

}