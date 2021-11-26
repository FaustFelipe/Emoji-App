package br.com.felipefaustini.domain.usecases.home

import br.com.felipefaustini.domain.models.Emoji
import br.com.felipefaustini.domain.models.User
import br.com.felipefaustini.domain.repository.EmojiRepository
import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val repository: EmojiRepository
) : IHomeUseCase {

    override suspend fun isExitingAUser(): Flow<Result<Boolean>> {
        val users = repository.findAllUsers().first()
        return flow {
            if (users is Result.Success) {
                if (users.data.isNotEmpty()) {
                    emit(Result.Success(true))
                } else {
                    emit(Result.Success(false))
                }
            } else {
                emit(Result.Error(ErrorEntity.Unknown))
            }
        }
    }

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

    override suspend fun getEmoji(): Flow<Result<List<Emoji>>> {
        val emojis = repository.findAllEmojis().first()

        return if (emojis.isEmpty()) {
            repository.getEmojis().onEach {
                if (it is Result.Success) {
                    it.data.forEach { emoji ->
                        repository.saveEmojis(emoji.name, emoji.url).collect()
                    }
                }
            }
        } else {
            repository.findAllEmojis()
                .map { Result.Success(it) }
        }
    }

}