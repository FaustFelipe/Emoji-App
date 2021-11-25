package br.com.felipefaustini.emojiapp.di

import br.com.felipefaustini.emojiapp.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(homeUseCase = get()) }
}