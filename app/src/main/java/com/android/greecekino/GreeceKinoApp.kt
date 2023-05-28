package com.android.greecekino

import android.app.Application
import com.android.greecekino.di.components.koinAppComponents
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class GreeceKinoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin(): KoinApplication {
        return startKoin {
            androidContext(this@GreeceKinoApp)
            modules(koinAppComponents)
        }
    }
}
