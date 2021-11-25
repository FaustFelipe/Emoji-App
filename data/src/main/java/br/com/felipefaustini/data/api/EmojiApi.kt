package br.com.felipefaustini.data.api

import br.com.felipefaustini.data.models.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EmojiApi {

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") username: String
    ): Response<UserResponse>

}