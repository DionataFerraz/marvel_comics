package com.dionataferraz.marvel_comics

import android.app.Application
import com.dionataferraz.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    presentationModule
                )
            )
        }
    }
}