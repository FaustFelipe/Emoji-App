package br.com.felipefaustini.emojiapp.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.felipefaustini.domain.models.User
import br.com.felipefaustini.domain.usecases.home.HomeUseCase
import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.emojiapp.MainCoroutineRule
import br.com.felipefaustini.emojiapp.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var homeUseCase: HomeUseCase

    @InjectMocks
    private lateinit var homeViewModel: HomeViewModel

    /* region getUser */
    @Test
    fun getUser_ifInvalidInputReturnInvalidMessage() {
        val expected = Unit
        homeViewModel.username = ""

        whenever(homeUseCase.getUser(""))
            .thenReturn(flowOf(Result.Error(ErrorEntity.InvalidFields)))

        homeViewModel.getUser()

        val result = homeViewModel.errorUnitLiveData.getOrAwaitValue()

        assertEquals(expected, result)
    }

    @Test
    fun getUser_shouldAnotherError() {
        val expected = "Error"
        homeViewModel.username = "faustfelipe"

        whenever(homeUseCase.getUser("faustfelipe"))
            .thenReturn(flowOf(Result.Error(ErrorEntity.Unknown)))

        homeViewModel.getUser()

        val result = homeViewModel.errorLiveData.getOrAwaitValue()

        assertEquals(expected, result)
    }

    @Test
    fun getUser_shouldReturnSuccess() {
        val expected = Unit
        homeViewModel.username = "faustfelipe"

        whenever(homeUseCase.getUser("faustfelipe"))
            .thenReturn(flowOf(Result.Success(User(name = "Felipe Faustini"))))

        homeViewModel.getUser()

        val result = homeViewModel.userSavedLiveData.getOrAwaitValue()

        assertEquals(expected, result)
    }
    /* endregion getUser */

}