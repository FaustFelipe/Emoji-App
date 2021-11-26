package br.com.felipefaustini.domain.di

import br.com.felipefaustini.domain.usecases.emojilist.EmojiListUseCase
import br.com.felipefaustini.domain.usecases.emojilist.IEmojiListUseCase
import br.com.felipefaustini.domain.usecases.googlerepos.GoogleReposUseCase
import br.com.felipefaustini.domain.usecases.googlerepos.IGoogleReposUseCase
import br.com.felipefaustini.domain.usecases.home.HomeUseCase
import br.com.felipefaustini.domain.usecases.home.IHomeUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single<IHomeUseCase> { HomeUseCase(repository = get()) }
    single<IEmojiListUseCase> { EmojiListUseCase(repository = get()) }
    single<IGoogleReposUseCase> { GoogleReposUseCase(repository = get()) }
}