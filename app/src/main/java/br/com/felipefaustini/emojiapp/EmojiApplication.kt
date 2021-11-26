package br.com.felipefaustini.emojiapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EmojiApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}