package br.com.felipefaustini.data.repository

import br.com.felipefaustini.data.api.EmojiApi
import br.com.felipefaustini.data.database.dao.UserDao
import br.com.felipefaustini.data.models.db.DBUser
import br.com.felipefaustini.data.models.mappers.UserMapper
import br.com.felipefaustini.data.models.response.UserResponse
import br.com.felipefaustini.domain.models.User
import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyZeroInteractions
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class EmojiRepositoryImplTest {

    @Mock
    private lateinit var emojiApi: EmojiApi

    @Mock
    private lateinit var userDao: UserDao

    private lateinit var repository: EmojiRepositoryImpl

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun beforeEachTest() {
        repository = EmojiRepositoryImpl(
            emojiApi,
            userDao,
            dispatcher
        )
    }

    /* region getUser */
    @Test
    fun getUser_verifyIfTheLastItemIsALalidUserObject() = dispatcher.runBlockingTest {
        val expected = Result.Success(User(name = "Felipe Faustini"))

        whenever(emojiApi.getUser("faustfelipe"))
            .thenReturn(Response.success(UserResponse(name = "Felipe Faustini")))

        val result = repository.getUser("faustfelipe").first()

        verify(emojiApi, times(1)).getUser("faustfelipe")
        assertEquals(expected, result)
    }

    @Test
    fun getUser_internetConnectionError() = dispatcher.runBlockingTest {
        val expected = Result.Error(ErrorEntity.Unauthorized)

        whenever(emojiApi.getUser("faustfelipe"))
            .thenReturn(
                Response.error(
                    401,
                    "".toResponseBody("application/json".toMediaTypeOrNull())
                )
            )

        val response = repository.getUser("faustfelipe").first()

        verify(emojiApi, times(1)).getUser("faustfelipe")
        assertEquals(expected, response)
    }
    /* endregion getUser */

    /* region saveUser */
    @Test
    fun saveUser_shouldReturnSuccessWhenSaving() = dispatcher.runBlockingTest {
        val expected = Result.Success(Unit)
        val user = User(
            login = "faustfelipe",
            name = "Felipe Faustini",
            id = 1L
        )
        val dbUser = UserMapper.map(user)

        whenever(userDao.insert(dbUser))
            .thenReturn(1L)

        val result = repository.saveUser(user).first()

        assertEquals(expected, result)
    }

    @Test
    fun saveUser_shouldReturnErrorWhenSaving() = dispatcher.runBlockingTest {
        val expected = Result.Error(ErrorEntity.Unknown)
        val user = User(
            login = "faustfelipe",
            name = "Felipe Faustini",
            id = 1L
        )
        val dbUser = UserMapper.map(user)

        whenever(userDao.insert(dbUser))
            .thenReturn(-1L)

        val result = repository.saveUser(user).first()

        assertEquals(expected, result)
    }
    /* endregion saveUser */

    /* region findUser */
    @Test
    fun findUser_shouldReturnAValidUser() = dispatcher.runBlockingTest {
        val expected = Result.Success(User(
            login = "faustfelipe",
            name = "Felipe Faustini",
            id = 1L
        ))

        whenever(userDao.findUser("faustfelipe"))
            .thenReturn(DBUser(
                login = "faustfelipe",
                name = "Felipe Faustini",
                userId = 1L
            ))

        val result = repository.findUser("faustfelipe").first()

        assertEquals(expected, result)
    }

    @Test
    fun findUser_shoudlReturnAnInvalidUser() = dispatcher.runBlockingTest {
        val expected = User()

        whenever(userDao.findUser("faustfelipe"))
            .thenReturn(null)

        val result = repository.findUser("faustfelipe").first()

        assertTrue(result is Result.Success)
        assertEquals(expected.id, (result as Result.Success).data.id)
    }
    /* endregion findUser */

    /* region isExisting */
    @Test
    fun isExisting_shouldReturnTrueIfExists() = dispatcher.runBlockingTest {
        val expected = true

        whenever(userDao.isExisting("faustfelipe"))
            .thenReturn(true)

        val result = repository.isExisting("faustfelipe").first()

        assertEquals(expected, result)
    }

    @Test
    fun isExisting_shouldReturnFalseIfNotExists() = dispatcher.runBlockingTest {
        val expected = false

        whenever(userDao.isExisting("faustfelipe"))
            .thenReturn(false)

        val result = repository.isExisting("faustfelipe").first()

        assertEquals(expected, result)
    }
    /* endregion isExisting */

}