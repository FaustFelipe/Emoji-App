package br.com.felipefaustini.data.di

import android.content.Context
import br.com.felipefaustini.data.database.EmojiDatabase
import br.com.felipefaustini.data.database.dao.EmojiDao
import br.com.felipefaustini.data.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModuleDI {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): EmojiDatabase {
        return EmojiDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideUserDao(database: EmojiDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideEmojiDao(database: EmojiDatabase): EmojiDao {
        return database.emojiDao()
    }

}