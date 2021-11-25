package br.com.felipefaustini.data.di

import br.com.felipefaustini.data.api.EmojiApi
import br.com.felipefaustini.data.database.dao.EmojiDao
import br.com.felipefaustini.data.database.dao.UserDao
import br.com.felipefaustini.data.repository.EmojiRepositoryImpl
import br.com.felipefaustini.domain.repository.EmojiRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {

    fun provideRepositoryModule(
        emojiApi: EmojiApi,
        userDao: UserDao,
        emojiDao: EmojiDao
    ): EmojiRepository {
        return EmojiRepositoryImpl(emojiApi, userDao, emojiDao, Dispatchers.IO)
    }

    single { provideRepositoryModule(emojiApi = get(), userDao = get(), emojiDao = get()) }

}