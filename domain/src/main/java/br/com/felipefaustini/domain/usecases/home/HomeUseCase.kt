package br.com.felipefaustini.domain.usecases.home

import br.com.felipefaustini.domain.models.User
import br.com.felipefaustini.domain.repository.EmojiRepository
import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class HomeUseCase(
    private val repository: EmojiRepository
): IHomeUseCase {

    override fun getUser(username: String): Flow<Result<User>> {
        if (username.isEmpty()) {
            return flow { emit(Result.Error(ErrorEntity.InvalidFields)) }
        }
        return repository.getUser(username)
    }

}