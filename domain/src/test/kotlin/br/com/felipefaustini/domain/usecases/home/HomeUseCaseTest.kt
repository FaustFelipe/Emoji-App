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
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.verifyZeroInteractions
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

    /* region getUser */
    @Test
    fun getUser_checkIfUsernameIsValid() = dispatcher.runBlockingTest {
        val expected = Result.Error(ErrorEntity.InvalidFields)

        val result = homeUseCase.getUser("").first()

        verifyZeroInteractions(repository)
        assertEquals(expected, result)
    }

    @Test
    fun getUser_userDoesntExistShouldRequestAndSaveIt() = dispatcher.runBlockingTest {
        val expected = Result.Success(User(name = name))

        whenever(repository.isExisting(username))
            .thenReturn(flowOf(false))
        whenever(repository.getUser(username))
            .thenReturn(flowOf(Result.Success(User(name = name))))
        whenever(repository.saveUser(User(name = name)))
            .thenReturn(flowOf(Result.Success(Unit)))

        val result = homeUseCase.getUser(username).first()

        verify(repository).isExisting(username)
        verify(repository).getUser(username)
        verify(repository).saveUser(User(name = name))
        verifyNoMoreInteractions(repository)
        assertEquals(expected, result)
    }

    @Test
    fun getUser_userExistsShouldGetUserFromDB() = dispatcher.runBlockingTest {
        val expected = Result.Success(User(name = name))

        whenever(repository.isExisting(username))
            .thenReturn(flowOf(true))
        whenever(repository.findUser(username))
            .thenReturn(flowOf(Result.Success(User(name = name))))

        val result = homeUseCase.getUser(username).first()

        verify(repository).isExisting(username)
        verify(repository).findUser(username)
        verifyNoMoreInteractions(repository)
        assertEquals(expected, result)
    }
    /* endregion getUser */

    companion object {
        private val name = "Felipe Faustini"
        private val username = "faustfelipe"
    }

}