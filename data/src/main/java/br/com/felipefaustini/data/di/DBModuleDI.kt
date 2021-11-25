package br.com.felipefaustini.data.di

import android.content.Context
import br.com.felipefaustini.data.database.EmojiDatabase
import br.com.felipefaustini.data.database.dao.UserDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(context: Context): EmojiDatabase {
        return EmojiDatabase.getDatabase(context)
    }

    single { provideDatabase(androidContext()) }

    fun provideUserDao(database: EmojiDatabase): UserDao {
        return database.userDao()
    }

    single { provideUserDao(get()) }

}