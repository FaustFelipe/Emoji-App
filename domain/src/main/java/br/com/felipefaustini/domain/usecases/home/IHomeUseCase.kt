package br.com.felipefaustini.domain.usecases.home

import br.com.felipefaustini.domain.models.Emoji
import br.com.felipefaustini.domain.models.User
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface IHomeUseCase {
    suspend fun isExitingAUser(): Flow<Result<Boolean>>
    suspend fun getUser(username: String): Flow<Result<User>>
    suspend fun getEmoji(): Flow<Result<List<Emoji>>>
}