package br.com.felipefaustini.emojiapp

import android.app.Application
import br.com.felipefaustini.data.di.databaseModule
import br.com.felipefaustini.data.di.networkModule
import br.com.felipefaustini.data.di.repositoryModule
import br.com.felipefaustini.domain.di.useCasesModule
import br.com.felipefaustini.emojiapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class EmojiApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@EmojiApplication)
            koin.loadModules(
                listOf(
                    networkModule,
                    repositoryModule,
                    databaseModule,
                    useCasesModule,
                    viewModelModule
                )
            )
        }
    }

    override fun onTerminate() {
        super.onTerminate()

        stopKoin()
    }

}