package br.com.felipefaustini.data.models.mappers

import br.com.felipefaustini.data.models.response.UserResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class UserMapperTest {

    @Test
    fun map_userMapperTest() {
        val userResponse = USER_RESPONSE

        val user = UserMapper.map(userResponse)

        assertEquals("Felipe Faustini", user.name)
    }

    companion object {
        private val USER_RESPONSE = UserResponse(
            name = "Felipe Faustini"
        )
    }

}