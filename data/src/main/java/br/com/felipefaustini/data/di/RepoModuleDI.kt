package br.com.felipefaustini.data.di

import br.com.felipefaustini.data.api.EmojiApi
import br.com.felipefaustini.data.database.dao.EmojiDao
import br.com.felipefaustini.data.database.dao.UserDao
import br.com.felipefaustini.data.repository.EmojiRepositoryImpl
import br.com.felipefaustini.domain.repository.EmojiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModuleDI {

    @Provides
    @Singleton
    fun provideRepositoryModule(
        emojiApi: EmojiApi,
        userDao: UserDao,
        emojiDao: EmojiDao
    ): EmojiRepository {
        return EmojiRepositoryImpl(emojiApi, userDao, emojiDao, Dispatchers.IO)
    }

}