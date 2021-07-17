package com.rosdyana.tomtommart

import android.app.Application
import android.content.Context
import com.rosdyana.tomtommart.di.dataModule
import com.rosdyana.tomtommart.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppController : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@AppController)
            modules(
                listOf(
                    dataModule,
                    viewModelModule
                )
            )
        }
        INSTANCE = this
    }

    companion object {
        private var INSTANCE: AppController? = null

        @JvmStatic
        fun getInstance(): Context {
            return INSTANCE as AppController
        }
    }
}