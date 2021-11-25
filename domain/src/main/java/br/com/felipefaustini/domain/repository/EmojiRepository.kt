package br.com.felipefaustini.domain.repository

import br.com.felipefaustini.domain.models.User
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface EmojiRepository {
    fun getUser(username: String): Flow<Result<User>>
}