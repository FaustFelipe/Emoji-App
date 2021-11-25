package br.com.felipefaustini.domain.usecases.home

import br.com.felipefaustini.domain.models.User
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface IHomeUseCase {
    suspend fun getUser(username: String): Flow<Result<User>>
}