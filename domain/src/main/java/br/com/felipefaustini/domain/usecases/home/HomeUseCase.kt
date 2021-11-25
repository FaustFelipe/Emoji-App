package br.com.felipefaustini.domain.usecases.home

import br.com.felipefaustini.domain.models.User
import br.com.felipefaustini.domain.repository.EmojiRepository
import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.flow.*

class HomeUseCase(
    private val repository: EmojiRepository
) : IHomeUseCase {

    override suspend fun getUser(username: String): Flow<Result<User>> {
        if (username.isEmpty()) {
            return flow { emit(Result.Error(ErrorEntity.InvalidFields)) }
        }

        val exists = repository.isExisting(username).first()
        return if (exists) {
            repository.findUser(username.lowercase())
        } else {
            repository.getUser(username)
                .onEach {
                    if (it is Result.Success) {
                        val user = it.data
                        user.login = user.login.lowercase()
                        repository.saveUser(user)
                            .collect()
                    }
                }
        }
    }

}