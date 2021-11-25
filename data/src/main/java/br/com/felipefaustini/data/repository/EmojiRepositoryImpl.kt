package br.com.felipefaustini.data.repository

import br.com.felipefaustini.data.api.EmojiApi
import br.com.felipefaustini.data.database.dao.UserDao
import br.com.felipefaustini.data.models.db.DBUser
import br.com.felipefaustini.data.models.mappers.UserMapper
import br.com.felipefaustini.data.utils.handleResponseCall
import br.com.felipefaustini.domain.models.User
import br.com.felipefaustini.domain.repository.EmojiRepository
import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class EmojiRepositoryImpl(
    private val api: EmojiApi,
    private val userDao: UserDao,
    private val coroutineContext: CoroutineContext
): EmojiRepository {

    override fun getUser(username: String): Flow<Result<User>> = flow {
        val request = api.getUser(username)
        val response = handleResponseCall(request) {
            UserMapper.map(request.body())
        }
        emit(response)
    }
        .catch {
            it.printStackTrace()
            emit(Result.Error(ErrorEntity.Unknown))
        }
        .flowOn(coroutineContext)

    override fun saveUser(user: User) = flow {
        val dbUser = UserMapper.map(user)
        val result = userDao.insert(dbUser)
        if (result != -1) {
            emit(Result.Success(Unit))
        } else {
            emit(Result.Error(ErrorEntity.Unknown))
        }
    }
        .catch {
            it.printStackTrace()
            emit(Result.Error(ErrorEntity.Unknown))
        }
        .flowOn(coroutineContext)

    override fun findUser(login: String): Flow<Result<User>> = flow<Result<User>> {
        val dbUser: DBUser? = userDao.findUser(login)
        if (dbUser != null)
            emit(Result.Success(UserMapper.map(dbUser)))
        else
            emit(Result.Success(User()))
    }
        .catch {
            it.printStackTrace()
            emit(Result.Error(ErrorEntity.Unknown))
        }
        .flowOn(coroutineContext)

    override fun isExisting(login: String): Flow<Boolean> = flow {
        emit(userDao.isExisting(login))
    }
        .catch {
            it.printStackTrace()
            emit(false)
        }
        .flowOn(coroutineContext)

}