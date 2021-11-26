package br.com.felipefaustini.data.api

import br.com.felipefaustini.data.models.response.ReposResponse
import br.com.felipefaustini.data.models.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EmojiApi {

    @GET("emojis")
    suspend fun getEmojis(
    ): Response<Map<String, String>>

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") username: String
    ): Response<UserResponse>

    @GET("users/{username}/repos")
    suspend fun getUserRepos(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<ReposResponse>>

}