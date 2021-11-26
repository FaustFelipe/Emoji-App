package br.com.felipefaustini.emojiapp.di

import br.com.felipefaustini.domain.repository.EmojiRepository
import br.com.felipefaustini.domain.usecases.emojilist.EmojiListUseCase
import br.com.felipefaustini.domain.usecases.emojilist.IEmojiListUseCase
import br.com.felipefaustini.domain.usecases.googlerepos.GoogleReposUseCase
import br.com.felipefaustini.domain.usecases.googlerepos.IGoogleReposUseCase
import br.com.felipefaustini.domain.usecases.home.HomeUseCase
import br.com.felipefaustini.domain.usecases.home.IHomeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModuleDI {

    @Provides
    @Singleton
    fun provideHomeUseCase(repository: EmojiRepository): IHomeUseCase {
        return HomeUseCase(repository = repository)
    }

    @Provides
    @Singleton
    fun provideEmojiUseCase(repository: EmojiRepository): IEmojiListUseCase {
        return EmojiListUseCase(repository = repository)
    }

    @Provides
    @Singleton
    fun provideGoogleReposUseCase(repository: EmojiRepository): IGoogleReposUseCase {
        return GoogleReposUseCase(repository = repository)
    }

}