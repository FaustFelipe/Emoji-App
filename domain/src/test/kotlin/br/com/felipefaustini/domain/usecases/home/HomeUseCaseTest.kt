package br.com.felipefaustini.domain.usecases.home

import br.com.felipefaustini.domain.models.User
import br.com.felipefaustini.domain.repository.EmojiRepository
import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class HomeUseCaseTest {

    @Mock
    private lateinit var repository: EmojiRepository

    private lateinit var homeUseCase: HomeUseCase

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun beforeEachTest() {
        homeUseCase = HomeUseCase(repository)
    }

    /* region user */
    @Test
    fun getUser_checkIfUserWasReturned() = dispatcher.runBlockingTest {
        val expected = Result.Success(User(name = "Felipe Faustini"))

        whenever(repository.getUser("faustfelipe"))
            .thenReturn(flowOf(Result.Success(User(name = "Felipe Faustini"))))

        val result = homeUseCase.getUser("faustfelipe").first()

        assertEquals(expected, result)
    }

    @Test
    fun getUser_checkIfErrorWasReturned() = dispatcher.runBlockingTest {
        val expected = Result.Error(ErrorEntity.Unknown)

        whenever(repository.getUser("faustfelipe"))
            .thenReturn(flowOf(Result.Error(ErrorEntity.Unknown)))

        val result = homeUseCase.getUser("faustfelipe").first()

        assertEquals(expected, result)
    }
    /* endregion user */

}