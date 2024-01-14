package com.example.sevenwindsproject.presentation

import android.app.Application
import com.example.sevenwindsproject.di.provideDispatcherModule
import com.example.sevenwindsproject.di.providesNetworkModule
import com.example.sevenwindsproject.di.providesPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SevenWindsApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SevenWindsApp)

            //Dispatcher module
            modules(provideDispatcherModule)

            //Network module
            modules(providesNetworkModule)

            //Presentation module
            modules(providesPresentationModule)
        }
    }
}
