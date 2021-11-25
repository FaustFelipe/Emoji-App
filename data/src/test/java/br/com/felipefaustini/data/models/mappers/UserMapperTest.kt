package br.com.felipefaustini.data.models.mappers

import br.com.felipefaustini.data.models.db.DBUser
import br.com.felipefaustini.data.models.response.UserResponse
import br.com.felipefaustini.domain.models.User
import org.junit.Assert.assertEquals
import org.junit.Test

class UserMapperTest {

    @Test
    fun map_userMapperTest() {
        val userResponse = USER_RESPONSE

        val user = UserMapper.map(userResponse)

        assertEquals("Felipe Faustini", user.name)
    }

    @Test
    fun map_dbUserMapperTest() {
        val user = USER

        val dbUser = UserMapper.map(user)

        assertEquals("faustfelipe", dbUser.login)
    }

    @Test
    fun map_userDbUserMapperTest() {
        val dbUser = DBUser(
            name = "Felipe Faustini",
            login = "faustfelipe",
            userId = 1L
        )

        val user = UserMapper.map(dbUser)

        assertEquals("faustfelipe", user.login)
    }

    companion object {
        private val USER_RESPONSE = UserResponse(
            name = "Felipe Faustini"
        )
        private val USER = User(
            name = "Felipe Faustini",
            login = "faustfelipe",
            id = 1L
        )
    }

}