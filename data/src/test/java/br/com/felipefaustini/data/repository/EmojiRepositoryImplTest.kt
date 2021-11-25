package br.com.felipefaustini.data.repository

import br.com.felipefaustini.data.api.EmojiApi
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

    private lateinit var repository: EmojiRepositoryImpl

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun beforeEachTest() {
        repository = EmojiRepositoryImpl(
            emojiApi,
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



}