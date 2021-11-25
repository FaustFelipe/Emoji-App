package br.com.felipefaustini.domain.di

import br.com.felipefaustini.domain.usecases.home.HomeUseCase
import br.com.felipefaustini.domain.usecases.home.IHomeUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory<IHomeUseCase> { HomeUseCase(repository = get()) }
}