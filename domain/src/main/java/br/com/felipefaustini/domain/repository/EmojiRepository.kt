package br.com.felipefaustini.domain.repository

import br.com.felipefaustini.domain.models.Emoji
import br.com.felipefaustini.domain.models.Repos
import br.com.felipefaustini.domain.models.User
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface EmojiRepository {
    fun findAllUsers(): Flow<Result<List<User>>>
    fun getUser(username: String): Flow<Result<User>>
    fun saveUser(user: User): Flow<Result<Unit>>
    fun findUser(login: String): Flow<Result<User>>
    fun isExisting(login: String): Flow<Boolean>
    fun getEmojis(): Flow<Result<List<Emoji>>>
    fun saveEmojis(key: String, value: String): Flow<Result<Unit>>
    fun findAllEmojis(): Flow<List<Emoji>>
    fun getUserRepos(username: String, page: Int, perPage: Int): Flow<Result<List<Repos>>>
}